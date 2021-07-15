package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.entity.ingredient.Ingredient;
import de.omb.ohmybeer.enums.Fermentation;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class Beer extends BaseEntity {

    @Column
    private String name;
    @ElementCollection
    private Set<String> photos;
    @ElementCollection
    private Map<Language, String> information;
    @ElementCollection
    private Map<Language, String> foodPairing;
    @OneToMany
    private Set<Ingredient> ingredients;
    @Column
    private Fermentation fermentation;


    // used in Search Algo
    @Column(length = 120)
    private int ibu;
    @Column
    private double gravity;
    @Column
    private double alcoholContent;
   /*
    @Column  TODO make an Enum
    private BeerType beerType;
    */

}
