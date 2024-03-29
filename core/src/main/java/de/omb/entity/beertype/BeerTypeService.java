package de.omb.entity.beertype;

import de.omb.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BeerTypeService extends GenericService<BeerType, Long, BeerTypeRepository> {

    @Autowired
    public BeerTypeService(BeerTypeRepository repository) {
        super(repository);
    }

    public BeerType findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public Set<BeerType> searchBeerTypes(String name) {
        return repository.findByNameStartsWithIgnoreCase(name);
    }

}
