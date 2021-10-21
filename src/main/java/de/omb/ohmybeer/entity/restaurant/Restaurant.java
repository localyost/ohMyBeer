package de.omb.ohmybeer.entity.restaurant;

import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.entity.beer.Beer;
import de.omb.ohmybeer.entity.resturanttype.RestaurantType;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.entity.translation.Translation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Setter
@Getter
public class Restaurant extends BaseEntity {

    @Column
    @NotNull
    private String name;
    @Column
    private String logo;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    private Socials socials;
    @ElementCollection
    private Set<String> photos;
    @OneToOne(cascade = CascadeType.ALL)
    private Translation information;
    @ManyToMany
    private Set<RestaurantType> restaurantTypes;
    @Column
    private boolean ombRecommend;
    @ManyToMany
    private Set<Beer> beers;
    @OneToOne
    private Beer signatureBeer;
}
