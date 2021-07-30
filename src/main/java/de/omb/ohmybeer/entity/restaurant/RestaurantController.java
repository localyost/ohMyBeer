package de.omb.ohmybeer.entity.restaurant;

import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.address.AddressService;
import de.omb.ohmybeer.entity.base.AbstractController;
import de.omb.ohmybeer.entity.webaddress.WebAddress;
import de.omb.ohmybeer.entity.webaddress.WebAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController extends AbstractController<Restaurant, RestaurantRepository, RestaurantService> {

    AddressService addressService;
    WebAddressService webAddressService;

    @Autowired
    public RestaurantController(
            RestaurantService restaurantService,
            AddressService addressService,
            WebAddressService webAddressService) {
        super(restaurantService);
        this.addressService = addressService;
        this.webAddressService = webAddressService;
    }

    @Override
    protected Restaurant onCreateOne(Restaurant restaurant) {
        Address address = restaurant.getAddress();
        if (address != null) {
            addressService.create(address);
        }
        WebAddress webAddress = restaurant.getWebAddress();
        if(webAddress != null) {
            webAddressService.create(webAddress);
        }
        return service.create(restaurant);
    }

}
