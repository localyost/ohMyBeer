package de.omb.webapi.controller.brewery;

import de.omb.entity.brewery.Brewery;
import de.omb.entity.brewery.BreweryRepository;
import de.omb.entity.brewery.BreweryService;
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
@RequestMapping(value = "/v1/breweries", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreweryController extends AbstractController<Brewery, BreweryRepository, BreweryService> {

    public BreweryController(BreweryService breweryService) {
        super(breweryService);
    }

    @Override
    protected AbstractDTO<Brewery> createDTO(Brewery entity, String[] fetchProps) {
        return new BreweryDTO(entity, fetchProps);
    }

    @GetMapping("/search")
    public Set<BreweryDTO> searchBreweryByName(@RequestParam String q) {
        return this.service.searchBreweries(q)
                .stream()
                .map(brewery -> new BreweryDTO(brewery, "name"))
                .collect(Collectors.toSet());
    }

}
