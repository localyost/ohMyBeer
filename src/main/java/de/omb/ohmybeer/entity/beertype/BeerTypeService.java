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

    public BeerType findOrCreate(String name) {
        BeerType beerType = repository.getByName(name);
        if (beerType == null) {
            beerType = new BeerType();
            beerType.setName(name);
            repository.save(beerType);
        }
        return beerType;
    }
}
