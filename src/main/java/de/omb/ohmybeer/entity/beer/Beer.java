package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.entity.beertype.BeerType;
import de.omb.ohmybeer.entity.brewery.Brewery;
import de.omb.ohmybeer.entity.ingredient.Ingredient;
import de.omb.ohmybeer.enums.Fermentation;
import de.omb.ohmybeer.enums.Language;
import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Data
public class Beer extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @ElementCollection
    private Set<String> photos;

    @ElementCollection
    @Column(name = "description", columnDefinition = "text")
    private Map<Language, String> description;
    @ElementCollection
    private Map<Language, String> foodPairing;
    @ManyToMany
    private Set<Ingredient> ingredients;
    @Column
    private Fermentation fermentation;
    @Column
    private String color;
    @OneToOne()
    private Brewery brewery;


    // used in Search Algo
    @Column(length = 120)
    private Integer ibu;
    @Column
    private Double gravity;
    @Column
    private Double alcoholContent;
    @OneToOne()
    private BeerType beerType;

    public void setDescription(Language language, String information) {
        if(this.description == null) { this.description = new HashMap<>(); }
        this.description.put(language, information);
    }

    public void setFoodPairing(Language language, String text) {
        if(this.foodPairing == null) { this.foodPairing = new HashMap<>(); }
        this.foodPairing.put(language, text);
    }

}
