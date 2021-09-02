package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.entity.beertype.BeerTypeDTO;
import de.omb.ohmybeer.entity.brewery.BreweryDTO;
import de.omb.ohmybeer.entity.ingredient.Ingredient;
import de.omb.ohmybeer.enums.Fermentation;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class BeerDTO extends AbstractDTO<Beer> {

    private String name;
    private Set<String> photos;
    private Map<Language, String> description;
    private Map<Language, String> foodPairing;
    private Set<Ingredient> ingredients;
    private Fermentation fermentation;
    private String color;
    private BreweryDTO brewery;
    private Integer ibu;
    private Double gravity;
    private Double alcoholContent;
    private BeerTypeDTO beerType;


    protected BeerDTO(Beer entity, String[] fetchProps) {
        super(entity, fetchProps);
    }

    @Override
    protected void setProperties() {
        this.isFilterProperty("name", beer -> this.name = beer.getName());
        this.isFilterProperty("photos", beer -> this.photos = beer.getPhotos());
        this.isFilterProperty("description", beer -> this.description = beer.getDescription());
        this.isFilterProperty("foodPairing", beer -> this.foodPairing = beer.getFoodPairing());
        this.isFilterProperty("ingredients", beer -> this.ingredients = beer.getIngredients());
        this.isFilterProperty("fermentation", beer -> this.fermentation = beer.getFermentation());
        this.isFilterProperty("color", beer -> this.color = beer.getColor());
        this.isFilterProperty("brewery", beer -> this.brewery = new BreweryDTO(beer.getBrewery(), "name"));
        this.isFilterProperty("ibu", beer -> this.ibu = beer.getIbu());
        this.isFilterProperty("gravity", beer -> this.gravity = beer.getGravity());
        this.isFilterProperty("alcoholContent", beer -> this.alcoholContent = beer.getAlcoholContent());
        this.isFilterProperty("beerType", beer -> this.beerType = new BeerTypeDTO(beer.getBeerType()));
    }
}
