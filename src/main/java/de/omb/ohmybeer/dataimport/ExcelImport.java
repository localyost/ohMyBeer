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
import de.omb.ohmybeer.entity.translation.Translation;
import de.omb.ohmybeer.entity.translation.TranslationService;
import de.omb.ohmybeer.enums.Fermentation;
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
import java.util.stream.Collectors;

@Component
public class ExcelImport {

    private final BreweryService breweryService;
    private final BeerService beerService;
    private final BeerTypeService beerTypeService;
    private final IngredientService ingredientService;
    private final TranslationService translationService;


    @Autowired
    public ExcelImport(
            BreweryService breweryService,
            BeerService beerService,
            BeerTypeService beerTypeService,
            IngredientService ingredientService,
            TranslationService translationService
    ) {
        this.breweryService = breweryService;
        this.beerService = beerService;
        this.beerTypeService = beerTypeService;
        this.ingredientService = ingredientService;
        this.translationService = translationService;
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
        elementsToParse
                .stream()
                .map(excelParsingMap -> excelParsingMap.get(ExcelColumn.breweryName))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Object::toString)
                .forEach(this::createBrewery);

        elementsToParse
                .stream()
                .map(excelParsingMap -> excelParsingMap.get(ExcelColumn.beerType))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Object::toString)
                .forEach(this::createBeerType);

        elementsToParse
                .stream()
                .map(excelParsingMap -> excelParsingMap.get(ExcelColumn.ingredients))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Object::toString)
                .forEach(this::createIngredient);

        elementsToParse
                .stream()
                .map(excelParsingMap -> excelParsingMap.get(ExcelColumn.description))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Object::toString);

        elementsToParse.forEach(parsingMap -> {
            createBeer(parsingMap);
        });
    }

    private void createIngredient(String name) {
        String[] arry = name.split(",");
        List<String> explodedNames = Arrays.asList(arry);
        for (String explodedName : explodedNames) {
            String trimmed = explodedName.trim();
            Ingredient ingredient = this.ingredientService.findByName(trimmed);
            if (ingredient == null) {
                ingredient = new Ingredient();
                ingredient.setName(trimmed);
                this.ingredientService.save(ingredient);
            }
        }
    }

    private void createBeerType(String beerTypeName) {
        String[] arry = beerTypeName.split(",");
        List<String> explodedNames = Arrays.asList(arry);
        for (String explodedName : explodedNames) {
            String trimmed = explodedName.trim();
            BeerType beerType = this.beerTypeService.getByName(trimmed);
            if (beerType == null) {
                beerType = new BeerType();
                beerType.setName(trimmed);
                this.beerTypeService.save(beerType);
            }
        }

    }

    private void createBrewery(String breweryName) {
        String trimmed = breweryName.trim();
        Brewery brewery = breweryService.getByName(trimmed);
        if (brewery == null) {
            brewery = new Brewery();
            brewery.setName(trimmed);
            breweryService.create(brewery);
        }
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

            if (description.isPresent()) {
                Translation translation = new Translation();
                translation.setDe(description.get().toString());
                translationService.create(translation);
                beer.setDescription(translation);
            }

            if (foodPairing.isPresent()) {
                Translation translation = new Translation();
                translation.setDe(foodPairing.get().toString());
                translationService.create(translation);
                beer.setFoodPairing(translation);
            }

            fermentation.ifPresent(fermType -> beer.setFermentation(getFermentationType(fermType.toString())));
            alcoholContent.ifPresent(content -> beer.setAlcoholContent(doubleFromPercent(content.toString())));
            gravity.ifPresent(g -> beer.setGravity(doubleFromPercent(g.toString())));
            ibuObt.ifPresent(ibu -> {
                double ibuDbl = (double) ibu;
                beer.setIbu((int)ibuDbl);
            });
            colorObt.ifPresent(color -> beer.setColor(color.toString()));

            map.get(ExcelColumn.beerType)
                    .ifPresent(o -> {
                        BeerType beerType = beerTypeService.getByName(o.toString().trim());
                        beer.setBeerType(beerType);
                    });

            map.get(ExcelColumn.breweryName)
                    .ifPresent(o -> {
                        Brewery brewery = this.breweryService.getByName(o.toString().trim());
                        if (brewery != null) {
                            beer.setBrewery(brewery);
                        }
                    });

            if (map.get(ExcelColumn.ingredients).isPresent()) {
                String[] arry =  map.get(ExcelColumn.ingredients).get().toString().split(",");
                List<String> explodedNames = Arrays.asList(arry);
                Set<Ingredient> ingredients = explodedNames.stream()
                        .map(String::trim)
                        .map(ingredientService::findByName)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                beer.setIngredients(ingredients);
            }

            return this.beerService.create(beer);
        }
        return null;
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
