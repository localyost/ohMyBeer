package de.omb.ohmybeer.entity.restaurant;

import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.entity.translation.Translation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
    @OneToOne
    private Address address;
    @OneToOne
    private Socials socials;
    @ElementCollection
    private Set<String> photos;
    @OneToOne
    private Translation information;



}
