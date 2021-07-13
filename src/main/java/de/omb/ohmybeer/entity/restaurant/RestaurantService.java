package de.omb.ohmybeer.entity.restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(Restaurant restaurant);
    List<Restaurant> getAll();
    Restaurant getOne(Long id);
    void deleteOne(Long id);
}
