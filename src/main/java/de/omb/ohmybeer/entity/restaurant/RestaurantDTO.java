package de.omb.ohmybeer.entity.restaurant;

import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.entity.translation.Translation;
import lombok.Getter;

import java.util.Set;

@Getter
public class RestaurantDTO extends AbstractDTO<Restaurant> {

    private String name;
    private String logo;
    private Address address;
    private Socials socials;
    private Set<String> photos;
    private Translation information;

    public RestaurantDTO(Restaurant entity) {
        super(entity);
    }

    @Override
    protected void setProperties() {
        name = entity.getName();
        logo = entity.getLogo();
        address = entity.getAddress();
        socials = entity.getSocials();
        photos = entity.getPhotos();
        information = entity.getInformation();
    }
}
