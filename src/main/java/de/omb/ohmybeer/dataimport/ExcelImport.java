package de.omb.ohmybeer.dataimport;

import de.omb.ohmybeer.dataimport.excel.ExcelColumn;
import de.omb.ohmybeer.dataimport.excel.ExcelParsingMap;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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

    public void parseExcelMap(File importFile) {
        try {
            FileInputStream fis = new FileInputStream(importFile);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            final List<ExcelParsingMap> elementsToParse = new ArrayList<>();
            int rowIndex = 0;
            for(Row row : sheet) {
                int HEADERS_ROW = 0;
                if (rowIndex > HEADERS_ROW) {
                    ExcelParsingMap parsingMap = new ExcelParsingMap();
                    EnumSet.allOf(ExcelColumn.class).forEach(column -> {
                        parsingMap.addElement(column, row);
                    });
                    elementsToParse.add(parsingMap);
                }
                rowIndex++;
            }
            buildEntities(elementsToParse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildEntities(List<ExcelParsingMap> elementsToParse) {
        elementsToParse.forEach(parsingMap -> {
            Beer beer = createBeer(parsingMap);
            Brewery brewery = createBrewery(parsingMap);
            BeerType beerType = createBeerType(parsingMap);
            Set<Ingredient> ingredients = createIngredients(parsingMap);
            persist(beer, brewery, beerType, ingredients);
        });
    }

    private void persist(Beer beer, Brewery brewery, BeerType beerType, Set<Ingredient> ingredients) {
        Brewery persistedBrewery = breweryService.getByName(brewery.getName());
        if (persistedBrewery == null) {
            persistedBrewery = breweryService.create(brewery);
        }
        BeerType persistedBeerType = beerTypeService.getByName(beerType.getName());
        if (persistedBeerType == null) {
            persistedBeerType = beerTypeService.create(beerType);
        }

        if (ingredients != null) {
            Set<Ingredient> persistedIngredients = new HashSet<>();
            for (Ingredient ingredient : ingredients) {
                Ingredient persistedIngredient = ingredientService.getByName(ingredient.getName());
                persistedIngredients.add(persistedIngredient != null ? persistedIngredient : ingredientService.create(ingredient));
            }
            beer.setIngredients(persistedIngredients);
        }

        beer.setBeerType(persistedBeerType);
        beer.setBrewery(persistedBrewery);
        beerService.create(beer);
    }

    private Brewery createBrewery(ExcelParsingMap map) {
        Optional<Object> value = map.get(ExcelColumn.breweryName);
        Brewery brewery = new Brewery();
        brewery.setName(value.get().toString());
        return brewery;
    }

    private Beer createBeer(ExcelParsingMap map) {
        Optional<Object> beerName = map.get(ExcelColumn.beerName);
        Optional<Object> description = map.get(ExcelColumn.description);
        Optional<Object> foodPairing = map.get(ExcelColumn.foodPairing);
        Optional<Object> fermentation = map.get(ExcelColumn.fermentation);
        Optional<Object> alcoholContent = map.get(ExcelColumn.alcoholContent);
        Optional<Object> gravity = map.get(ExcelColumn.gravity);
        Optional<Object> colorObt = map.get(ExcelColumn.color);
        Optional<Object> ibuObt = map.get(ExcelColumn.ibu);
        if (beerName.isPresent()) {
            Beer beer = new Beer();
            beer.setName(beerName.get().toString());
            description.ifPresent(desc -> beer.setDescription(Language.de, desc.toString()));
            foodPairing.ifPresent(pairing -> beer.setFoodPairing(Language.de, pairing.toString()));
            fermentation.ifPresent(fermType -> beer.setFermentation(getFermentationType(fermType.toString())));
            alcoholContent.ifPresent(content -> beer.setAlcoholContent(doubleFromPercent(content.toString())));
            gravity.ifPresent(g -> beer.setGravity(doubleFromPercent(g.toString())));
            ibuObt.ifPresent(ibu -> {
                double ibuDbl = (double) ibu;
                beer.setIbu((int)ibuDbl);
            });
            colorObt.ifPresent(color -> beer.setColor(color.toString()));
            return beer;
        }
        return null;
    }

    private BeerType createBeerType(ExcelParsingMap map) {
        Optional<Object> value = map.get(ExcelColumn.beerType);
        BeerType beerType = new BeerType();
        beerType.setName(value.get().toString());
        return beerType;
    }

    private Set<Ingredient> createIngredients(ExcelParsingMap map){
        Optional<Object> ingredientsObt = map.get(ExcelColumn.ingredients);
        if (ingredientsObt.isPresent()) {
            Set<Ingredient> ingredients = new HashSet<>();
            String[] arry = ingredientsObt.get().toString().split(",");
            for (String s : arry) {
                String trimmed = s.trim();
                Ingredient ingredient = new Ingredient();
                ingredient.setName(trimmed);
                ingredient.addLabel(Language.de, trimmed);
                ingredients.add(ingredient);
            }
            return ingredients;
        }
        return null;
    }

    public Optional<Integer> getIBU(Cell cell) {
        double value = cell.getNumericCellValue();
        return Optional.of((int)value);
    }

    private Fermentation getFermentationType(String fermentation) {
        if (fermentation.equals("obergärig")) {
            return Fermentation.TOP;
        }
        if (fermentation.equals("untergärig")) {
            return Fermentation.BOTTOM;
        }
        return null;
    }

    private Double doubleFromPercent(String stringParse) {
        String percent = stringParse.split("%")[0];
        return Double.parseDouble(percent);
    }

}
