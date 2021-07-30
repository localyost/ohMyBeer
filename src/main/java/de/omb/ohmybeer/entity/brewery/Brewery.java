package de.omb.ohmybeer.entity.brewery;

import de.omb.ohmybeer.entity._base.BaseEntity;
import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.beer.Beer;
import de.omb.ohmybeer.entity.webaddress.WebAddress;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class Brewery extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column
    private String logo;
    @OneToOne
    private Address address;
    @OneToOne
    private WebAddress webAddress;
    @ElementCollection
    private Set<String> photos;
    @ElementCollection
    private Map<Language, String> information;
    @OneToMany
    private Set<Beer> beers;
}
