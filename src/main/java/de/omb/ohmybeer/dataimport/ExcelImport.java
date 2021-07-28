package de.omb.ohmybeer.dataimport;

import de.omb.ohmybeer.entity.beer.Beer;
import de.omb.ohmybeer.entity.beer.BeerService;
import de.omb.ohmybeer.entity.beertype.BeerType;
import de.omb.ohmybeer.entity.beertype.BeerTypeService;
import de.omb.ohmybeer.entity.brewery.Brewery;
import de.omb.ohmybeer.entity.brewery.BreweryService;
import de.omb.ohmybeer.entity.ingredient.Ingredient;
import de.omb.ohmybeer.entity.ingredient.IngredientService;
import de.omb.ohmybeer.enums.Fermentation;
import de.omb.ohmybeer.enums.Language;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ExcelImport {

    private final BreweryService breweryService;
    private final BeerService beerService;
    private final BeerTypeService beerTypeService;
    private final IngredientService ingredientService;


    @Autowired
    public ExcelImport(
            BreweryService breweryService,
            BeerService beerService,
            BeerTypeService beerTypeService,
            IngredientService ingredientService
    ) {
        this.breweryService = breweryService;
        this.beerService = beerService;
        this.beerTypeService = beerTypeService;
        this.ingredientService = ingredientService;
    }

    public void start() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("imports/beer.xlsx");
        try {
            if (url != null) {
                Path path = Paths.get(url.toURI());
                FileInputStream fis = new FileInputStream(path.toFile());
                Workbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0);
                int rowIndex = 0;
                for(Row row : sheet) {
                    if (rowIndex > 0) {
                        Beer beer = createBeer(row.getCell(1));
                        System.out.println(rowIndex + " --> "+beer.getName());
                        Brewery brewery = initBrewery(row.getCell(0));
                        beer.setBrewery(brewery);
                        BeerType beerType = initBeerType(row.getCell(2));
                        beer.setBeerType(beerType);
                        if (row.getCell(3) != null) {
                            String information = row.getCell(3).getStringCellValue();
                            beer.setInformation(Language.de, information);
                        }
                        if (row.getCell(4) != null) {
                            Set<Ingredient> ingredients = initIngredients(row.getCell(4));
                            beer.setIngredients(ingredients);
                        }
                        if (row.getCell(5) != null) {
                            beer.setFoodPairing(Language.de, row.getCell(5).getStringCellValue());
                        }
                        if (row.getCell(6) != null) {
                            beer.setFermentation(getFermentationType(row.getCell(6)));
                        }
                        if (row.getCell(7) != null) {
                            // TODO COlOR
                        }
                        if (row.getCell(8) != null) {
                            doubleFromPercent(row.getCell(8)).ifPresent(beer::setAlcoholContent);
                        }
                        if (row.getCell(9) != null) {
                            doubleFromPercent(row.getCell(9)).ifPresent(beer::setGravity);
                        }
                        if (row.getCell(10) != null) {
                            getIBU(row.getCell(10)).ifPresent(beer::setIbu);
                        }

                        beerService.save(beer);

                    }

                   rowIndex++;
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Brewery initBrewery(Cell cell) {
       return breweryService.findOrCreate(cell.getStringCellValue());
    }

    public Beer createBeer(Cell cell) {
        Beer beer = new Beer();
        beer.setName(cell.getStringCellValue());
        return beer;
    }

    public BeerType initBeerType(Cell cell) {
       return beerTypeService.findOrCreate(cell.getStringCellValue());
    }

    public Set<Ingredient> initIngredients(Cell cell){
        Set<Ingredient> ingredients = new HashSet<>();
        String ingredientString = cell.getStringCellValue();
        String[] arry = ingredientString.split(",");
        for (String s : arry) {
            String trimmed = s.trim();
            Ingredient ingredient = ingredientService.getByName(trimmed);
            if (ingredient == null) {
                ingredient = new Ingredient();
                ingredient.setName(trimmed);
                ingredient.addLabel(Language.de, trimmed);
                ingredientService.save(ingredient);
            }

            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public Fermentation getFermentationType(Cell cell) {
        String deType = cell.getStringCellValue();
        if (deType.equals("obergärig")) {
            return Fermentation.TOP;
        }
        if (deType.equals("untergärig")) {
            return Fermentation.BOTTOM;
        }
        return null;
    }

    public Optional<Double> doubleFromPercent(Cell cell) {
        String percent = cell.getStringCellValue().split("%")[0];
        Double value = Double.parseDouble(percent);
        return Optional.of(value);
    }

    public Optional<Integer> getIBU(Cell cell) {
        double value = cell.getNumericCellValue();
        return Optional.of((int)value);
    }

}
