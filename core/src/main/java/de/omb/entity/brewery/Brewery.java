package de.omb.entity.brewery;

import de.omb.entity.address.Address;
import de.omb.entity.base.BaseEntity;
import de.omb.entity.beer.Beer;
import de.omb.entity.socials.Socials;
import de.omb.entity.translation.Translation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
public class Brewery extends BaseEntity {
    @Column
    @NotNull
    private String name;
    @Column
    private String legalEntity;
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
    @OneToMany(mappedBy = "brewery")
    private Set<Beer> beers;
    @Column
    private boolean ombRecommend;
}
