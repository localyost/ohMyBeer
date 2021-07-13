package de.omb.ohmybeer.controller;

import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.address.AddressService;
import de.omb.ohmybeer.entity.restaurant.Restaurant;
import de.omb.ohmybeer.entity.restaurant.RestaurantService;
import de.omb.ohmybeer.entity.webaddress.WebAddress;
import de.omb.ohmybeer.entity.webaddress.WebAddressService;
import de.omb.ohmybeer.enums.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    RestaurantService restaurantService;
    AddressService addressService;
    WebAddressService webAddressService;

    @Autowired
    public RestaurantController(
            RestaurantService restaurantService,
            AddressService addressService,
            WebAddressService webAddressService) {
        this.restaurantService = restaurantService;
        this.addressService = addressService;
        this.webAddressService = webAddressService;
    }

    @GetMapping
    @ResponseBody
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Restaurant getOne(@PathVariable Long id) {
        return restaurantService.getOne(id);
    }

    @PostMapping
    @ResponseBody
    public Restaurant addOne(Restaurant restaurant) {

        Address address = restaurant.getAddress();
        if (address != null) {
            addressService.addAddress(address);
        }

        WebAddress webAddress = restaurant.getWebAddress();
        if(webAddress != null) {
            webAddressService.addWebAddress(webAddress);
        }

        return restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        restaurantService.deleteOne(id);
    }

    @PutMapping
    public void updateOne() {

    }



}
