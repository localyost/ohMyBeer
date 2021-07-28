package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity._base.BaseEntity;
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

    @Column
    private String name;
    @ElementCollection
    private Set<String> photos;

    @ElementCollection
    @Column(name = "information", columnDefinition = "text")
    private Map<Language, String> information;
    @ElementCollection
    private Map<Language, String> foodPairing;
    @ManyToMany
    private Set<Ingredient> ingredients;
    @Column
    private Fermentation fermentation;
    @OneToOne
    private Brewery brewery;


    // used in Search Algo
    @Column(length = 120)
    private Integer ibu;
    @Column
    private Double gravity;
    @Column
    private Double alcoholContent;
    @OneToOne
    private BeerType beerType;

    public void setInformation(Language language, String information) {
        if(this.information == null) { this.information = new HashMap<>(); }
        this.information.put(language, information);
    }

    public void setFoodPairing(Language language, String text) {
        if(this.foodPairing == null) { this.foodPairing = new HashMap<>(); }
        this.foodPairing.put(language, text);
    }

}
