package de.omb.webapi.controller.beer;

import de.omb.entity.beer.Beer;
import de.omb.entity.translation.Translation;
import de.omb.enums.Fermentation;
import de.omb.webapi.controller.base.AbstractDTO;
import de.omb.webapi.controller.beertype.BeerTypeDTO;
import de.omb.webapi.controller.brewery.BreweryDTO;
import de.omb.webapi.controller.ingredient.IngredientDTO;
import de.omb.webapi.controller.translation.TranslationDTO;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BeerDTO extends AbstractDTO<Beer> {

    private String name;
    private Set<String> photos;
    private TranslationDTO description;
    private TranslationDTO foodPairing;
    private Set<IngredientDTO> ingredients;
    private Fermentation fermentation;
    private String color;
    private BreweryDTO brewery;
    private Integer ibu;
    private Double gravity;
    private Double alcoholContent;
    private Set<BeerTypeDTO> beerTypes;


    protected BeerDTO(Beer entity, String[] fetchProps) {
        super(entity, fetchProps);
    }

    @Override
    protected void setProperties() {
        this.isFilterProperty("name", beer -> this.name = beer.getName());
        this.isFilterProperty("photos", beer -> this.photos = beer.getPhotos());
        this.isFilterProperty("description", beer -> this.description = getTranslation(beer.getDescription()));
        this.isFilterProperty("foodPairing", beer -> this.foodPairing = getTranslation(beer.getFoodPairing()));
        this.isFilterProperty("ingredients", beer -> this.ingredients = beer.getIngredients()
                .stream().map(IngredientDTO::new).
                collect(Collectors.toSet()));
        this.isFilterProperty("fermentation", beer -> this.fermentation = beer.getFermentation());
        this.isFilterProperty("color", beer -> this.color = beer.getColor());
        this.isFilterProperty("brewery", beer -> this.brewery = new BreweryDTO(beer.getBrewery(), "name"));
        this.isFilterProperty("ibu", beer -> this.ibu = beer.getIbu());
        this.isFilterProperty("gravity", beer -> this.gravity = beer.getGravity());
        this.isFilterProperty("alcoholContent", beer -> this.alcoholContent = beer.getAlcoholContent());
        this.isFilterProperty("beerTypes", beer -> this.beerTypes = beer.getBeerTypes()
                .stream().map(BeerTypeDTO::new)
                .collect(Collectors.toSet()));
    }

    private TranslationDTO getTranslation(Translation translation) {
        return translation != null ?
                new TranslationDTO(translation) :
                null;
    }
}
