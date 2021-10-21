package de.omb.ohmybeer.entity.restaurant;

import de.omb.ohmybeer.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService extends GenericService<Restaurant, Long, RestaurantRepository> {

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        super(restaurantRepository);
    }

    public Restaurant getByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }
}
