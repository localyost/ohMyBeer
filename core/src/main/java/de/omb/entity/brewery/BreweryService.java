package de.omb.entity.brewery;

import de.omb.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BreweryService extends GenericService<Brewery, Long, BreweryRepository> {

    @Autowired
    public BreweryService(BreweryRepository repository) {
        super(repository);
    }

    public Brewery getByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public Set<Brewery> searchBreweries(String search) {
        return repository.findByNameStartsWithIgnoreCase(search);
    }

}
