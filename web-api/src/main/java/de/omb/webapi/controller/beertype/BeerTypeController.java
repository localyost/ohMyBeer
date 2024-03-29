package de.omb.webapi.controller.beertype;

import de.omb.entity.beertype.BeerType;
import de.omb.entity.beertype.BeerTypeRepository;
import de.omb.entity.beertype.BeerTypeService;
import de.omb.webapi.controller.base.AbstractController;
import de.omb.webapi.controller.base.AbstractDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/beerType", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerTypeController extends AbstractController<BeerType, BeerTypeRepository, BeerTypeService>{

    protected BeerTypeController(BeerTypeService service) { super(service); }

    @Override
    protected AbstractDTO<BeerType> createDTO(BeerType entity, String[] fetchProps) {
        return new BeerTypeDTO(entity);
    }

    @GetMapping("/search")
    public Set<BeerTypeDTO> searchBeerTypes(@RequestParam String q) {
        return this.service.searchBeerTypes(q)
                .stream()
                .map(BeerTypeDTO::new)
                .collect(Collectors.toSet());
    }

}
