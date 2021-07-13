package de.omb.ohmybeer.entity.address;

import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.enums.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Address extends BaseEntity {

    //TODO LATLNG

    @Column
    private String street;
    @Column
    private String houseNumber;
    @Column
    private String postcode;
    @Column
    private String city;
    @Column
    private Country country;

}
