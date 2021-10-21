package de.omb.ohmybeer.dataimport;

import de.omb.ohmybeer.dataimport.excel.BeerImportExcelColumn;
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
import de.omb.ohmybeer.enums.Fermentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BeerImport {

    private final BreweryService breweryService;
    private final BeerService beerService;
    private final BeerTypeService beerTypeService;
    private final IngredientService ingredientService;

    public void run(List<ExcelParsingMap> elementsToParse) {
        elementsToParse
                .stream()
                .map(excelParsingMap -> excelParsingMap.get(BeerImportExcelColumn.breweryName))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Object::toString)
                .forEach(this::createBrewery);

        elementsToParse
                .stream()
                .map(excelParsingMap -> excelParsingMap.get(BeerImportExcelColumn.beerType))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Object::toString)
                .forEach(this::createBeerType);

        elementsToParse
                .stream()
                .map(excelParsingMap -> excelParsingMap.get(BeerImportExcelColumn.ingredients))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Object::toString)
                .forEach(this::createIngredient);

        elementsToParse
                .stream()
                .map(excelParsingMap -> excelParsingMap.get(BeerImportExcelColumn.description))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Object::toString);

        elementsToParse.forEach(this::createBeer);
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
            BeerType beerType = this.beerTypeService.findByName(trimmed);
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
        Optional<Object> beerName = map.get(BeerImportExcelColumn.beerName);
        Optional<Object> description = map.get(BeerImportExcelColumn.description);
        Optional<Object> foodPairing = map.get(BeerImportExcelColumn.foodPairing);
        Optional<Object> fermentation = map.get(BeerImportExcelColumn.fermentation);
        Optional<Object> alcoholContent = map.get(BeerImportExcelColumn.alcoholContent);
        Optional<Object> gravity = map.get(BeerImportExcelColumn.gravity);
        Optional<Object> colorObt = map.get(BeerImportExcelColumn.color);
        Optional<Object> ibuObt = map.get(BeerImportExcelColumn.ibu);
        if (beerName.isPresent()) {
            String name = beerName.get().toString().trim();
            Optional<Beer> beerOptional = Optional.ofNullable(beerService.getByName(name));
            Beer beer = beerOptional.orElseGet(Beer::new);
            beer.setName(name);

            if (description.isPresent()) {
                Translation translation = new Translation();
                translation.setDe(description.get().toString());
//                translationService.create(translation);
                beer.setDescription(translation);
            }

            if (foodPairing.isPresent()) {
                Translation translation = new Translation();
                translation.setDe(foodPairing.get().toString());
//                translationService.create(translation);
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

            map.get(BeerImportExcelColumn.beerType).ifPresent(o -> {
                        String[] arry =  map.get(BeerImportExcelColumn.beerType).get().toString().split(",");
                        List<String> explodedNames = Arrays.asList(arry);
                        Set<BeerType> beerTypes = explodedNames.stream()
                                .map(String::trim)
                                .map(beerTypeService::findByName)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toSet());

                        beer.setBeerTypes(beerTypes);
                    });

            map.get(BeerImportExcelColumn.breweryName)
                    .ifPresent(o -> {
                        Brewery brewery = this.breweryService.getByName(o.toString().trim());
                        if (brewery != null) {
                            beer.setBrewery(brewery);
                        }
                    });

            if (map.get(BeerImportExcelColumn.ingredients).isPresent()) {
                String[] arry =  map.get(BeerImportExcelColumn.ingredients).get().toString().split(",");
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
