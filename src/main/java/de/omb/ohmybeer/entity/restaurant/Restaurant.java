package de.omb.ohmybeer.entity.restaurant;

import de.omb.ohmybeer.entity._base.BaseEntity;
import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.webaddress.WebAddress;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Map;
import java.util.Set;

@Entity
@Setter
@Getter
public class Restaurant extends BaseEntity {

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



}
