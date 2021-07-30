package de.omb.ohmybeer.entity.brewery;

import de.omb.ohmybeer.entity._base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BreweryService extends GenericService<Brewery, Long, BreweryRepository> {

    @Autowired
    public BreweryService(BreweryRepository repository) {
        super(repository);
    }

    public Brewery getByName(String name) {
        return repository.getByName(name);
    }

}
