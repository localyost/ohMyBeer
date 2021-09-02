package de.omb.ohmybeer.entity.beertype;

import de.omb.ohmybeer.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BeerTypeService extends GenericService<BeerType, Long, BeerTypeRepository> {

    @Autowired
    public BeerTypeService(BeerTypeRepository repository) {
        super(repository);
    }

    public BeerType getByName(String name) {
        return repository.getByName(name);
    }

    public Set<BeerType> searchBeerTypes(String name) {
        return repository.findByNameStartsWithIgnoreCase(name);
    }

}
