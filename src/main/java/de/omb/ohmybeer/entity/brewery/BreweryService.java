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

    public Brewery findOrCreate(String breweryName) {
        Brewery brewery = repository.getByName(breweryName);
        if (brewery == null) {
            brewery = new Brewery();
            brewery.setName(breweryName);
            repository.save(brewery);
        }
        return brewery;
    }
}
