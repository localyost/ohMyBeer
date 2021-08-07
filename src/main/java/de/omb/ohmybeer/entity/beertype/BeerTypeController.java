package de.omb.ohmybeer.entity.beertype;

import de.omb.ohmybeer.entity.base.AbstractController;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/beerTypes", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerTypeController extends AbstractController<BeerType, BeerTypeRepository, BeerTypeService>{

    protected BeerTypeController(BeerTypeService service) { super(service); }

    @Override
    protected AbstractDTO<BeerType> createDTO(BeerType entity, String[] fetchProps) {
        return new BeerTypeDTO(entity);
    }

}
