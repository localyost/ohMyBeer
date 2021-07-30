package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService extends GenericService<Beer, Long, BeerRepository> {

    @Autowired
    public BeerService(BeerRepository repository) {
        super(repository);
    }
}
