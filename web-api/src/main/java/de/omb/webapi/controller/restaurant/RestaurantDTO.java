package de.omb.webapi.controller.restaurant;

import de.omb.entity.address.Address;
import de.omb.entity.restaurant.Restaurant;
import de.omb.entity.socials.Socials;
import de.omb.entity.translation.Translation;
import de.omb.webapi.controller.base.AbstractDTO;
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
