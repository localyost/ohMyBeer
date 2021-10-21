package de.omb.ohmybeer.entity.resturanttype;

import de.omb.ohmybeer.entity.base.GenericService;
import org.springframework.stereotype.Service;

@Service
public class RestaurantTypeService extends GenericService<RestaurantType, Long, RestaurantTypeRepository> {

    public RestaurantTypeService(RestaurantTypeRepository repository) {
        super(repository);
    }

    public RestaurantType getByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }
}
