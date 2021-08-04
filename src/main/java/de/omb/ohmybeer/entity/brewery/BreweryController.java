package de.omb.ohmybeer.entity.brewery;

import de.omb.ohmybeer.entity.base.AbstractController;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/breweries", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreweryController extends AbstractController<Brewery, BreweryRepository, BreweryService> {

    public BreweryController(BreweryService breweryService) {
        super(breweryService);
    }

    @Override
    protected AbstractDTO<Brewery> createDTO(Brewery entity) {
        return new BreweryDTO(entity).setProperties();
    }

}
