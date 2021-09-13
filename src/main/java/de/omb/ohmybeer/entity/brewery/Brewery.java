package de.omb.ohmybeer.entity.brewery;

import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.entity.beer.Beer;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.entity.translation.Translation;
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

}
