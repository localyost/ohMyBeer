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
    private String logo;
    @OneToOne
    private Address address;
    @OneToOne
    private Socials socials;
    @ElementCollection
    private Set<String> photos;
    @OneToOne
    private Translation information;
    @OneToMany(mappedBy = "brewery")
    private Set<Beer> beers;

}
