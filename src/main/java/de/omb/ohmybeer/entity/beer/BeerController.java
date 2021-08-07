package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.AbstractController;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/beers", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerController extends AbstractController<Beer, BeerRepository, BeerService> {

    @Autowired
    public BeerController(BeerService beerService) { super(beerService); }

    @Override
    protected AbstractDTO<Beer> createDTO(Beer entity, String[] fetchProps) {
        return new BeerDTO(entity, fetchProps);
    }
}
