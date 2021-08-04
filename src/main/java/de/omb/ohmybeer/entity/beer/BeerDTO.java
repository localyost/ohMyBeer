package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.AbstractDTO;
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
    private Long breweryId;
    private Integer ibu;
    private Double gravity;
    private Double alcoholContent;
    private String beerType;

    protected BeerDTO(Beer entity) {
        super(entity);
    }

    @Override
    protected BeerDTO setProperties() {
        name = entity.getName();
        photos = entity.getPhotos();
        description = entity.getDescription();
        foodPairing = entity.getFoodPairing();
        ingredients = entity.getIngredients();
        fermentation = entity.getFermentation();
        color = entity.getColor();
        breweryId = entity.getBrewery().getId();
        ibu = entity.getIbu();
        gravity = entity.getGravity();
        alcoholContent = entity.getAlcoholContent();
        beerType = entity.getBeerType().getName();
        return this;
    }

    public BeerDTO setBasicFields() {
        name = entity.getName();
        photos = entity.getPhotos();
        fermentation = entity.getFermentation();
        color = entity.getColor();
        ibu = entity.getIbu();
        gravity = entity.getGravity();
        alcoholContent = entity.getAlcoholContent();
        beerType = entity.getBeerType().getName();
        return this;
    }
}
