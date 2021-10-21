package de.omb.entity.address;

import de.omb.entity.base.BaseEntity;
import de.omb.enums.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Address extends BaseEntity {

    @Column
    private Double latitude;
    @Column
    private Double longitude;
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
