package de.omb.entity.beer;


import de.omb.entity.base.BaseEntity;
import de.omb.entity.beertype.BeerType;
import de.omb.entity.brewery.Brewery;
import de.omb.entity.ingredient.Ingredient;
import de.omb.entity.translation.Translation;
import de.omb.enums.Fermentation;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Data
public class Beer extends BaseEntity {

    @Column
    @NotNull
    private String name;

    @ElementCollection
    private Set<String> photos;

    @OneToOne(cascade = CascadeType.ALL)
    private Translation description;

    @OneToOne(cascade = CascadeType.ALL)
    private Translation foodPairing;

    @ManyToMany
    private Set<Ingredient> ingredients;
    @Column
    private Fermentation fermentation;
    @Column
    private String color;
    @ManyToOne
    private Brewery brewery;


    // used in Search Algo
    @Column(length = 120)
    private Integer ibu;
    @Column
    private Double gravity;
    @Column
    private Double alcoholContent;
    @ManyToMany
    private Set<BeerType> beerTypes;
}
