package de.omb.ohmybeer.entity.beertype;

import de.omb.ohmybeer.entity._base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerTypeService extends GenericService<BeerType, Long, BeerTypeRepository> {

    @Autowired
    public BeerTypeService(BeerTypeRepository repository) {
        super(repository);
    }
}
