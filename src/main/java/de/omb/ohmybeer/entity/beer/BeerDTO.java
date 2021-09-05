package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.entity.beertype.BeerTypeDTO;
import de.omb.ohmybeer.entity.brewery.BreweryDTO;
import de.omb.ohmybeer.entity.ingredient.IngredientDTO;
import de.omb.ohmybeer.entity.translation.Translation;
import de.omb.ohmybeer.entity.translation.TranslationDTO;
import de.omb.ohmybeer.enums.Fermentation;
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
    private BeerTypeDTO beerType;


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
        this.isFilterProperty("beerType", beer -> this.beerType = new BeerTypeDTO(beer.getBeerType()));
    }

    private TranslationDTO getTranslation(Translation translation) {
        return translation != null ?
                new TranslationDTO(translation) :
                null;
    }
}
