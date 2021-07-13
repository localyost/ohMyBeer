package de.omb.ohmybeer.entity.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public Restaurant getOne(Long id) {
        return this.restaurantRepository.getById(id);
    }

    @Override
    public void deleteOne(Long id) {
        this.restaurantRepository.deleteById(id);
    }
}
