package de.omb.ohmybeer.entity.restaurant;

import de.omb.ohmybeer.entity.address.AddressService;
import de.omb.ohmybeer.entity.base.AbstractController;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.entity.socials.SocialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController extends AbstractController<Restaurant, RestaurantRepository, RestaurantService> {

    AddressService addressService;
    SocialsService socialsService;

    @Autowired
    public RestaurantController(
            RestaurantService restaurantService,
            AddressService addressService,
            SocialsService socialsService) {
        super(restaurantService);
        this.addressService = addressService;
        this.socialsService = socialsService;
    }

    @Override
    protected AbstractDTO<Restaurant> createDTO(Restaurant entity, String[] fetchProps) {
        return new RestaurantDTO(entity);
    }

//    @Override
//    protected AbstractDTO<Restaurant> onCreateOne(Restaurant restaurant) {
//        Address address = restaurant.getAddress();
//        if (address != null) {
//            addressService.create(address);
//        }
//        Socials socials = restaurant.getSocials();
//        if(socials != null) {
//            socialsService.create(socials);
//        }
//        return createDTO(service.create(restaurant));
//    }

}
