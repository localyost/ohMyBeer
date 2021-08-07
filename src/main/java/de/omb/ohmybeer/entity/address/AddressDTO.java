package de.omb.ohmybeer.entity.address;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.enums.Country;
import lombok.Getter;

@Getter
public class AddressDTO extends AbstractDTO<Address> {

    private Double latitude;
    private Double longitude;
    private String street;
    private String houseNumber;
    private String postcode;
    private String city;
    private Country country;

    public AddressDTO(Address entity) {
        super(entity);
    }

    @Override
    protected void setProperties() {
        latitude = entity.getLatitude();
        longitude = entity.getLongitude();
        street = entity.getStreet();
        houseNumber = entity.getHouseNumber();
        postcode = entity.getPostcode();
        city = entity.getCity();
        country = entity.getCountry();
    }
}
