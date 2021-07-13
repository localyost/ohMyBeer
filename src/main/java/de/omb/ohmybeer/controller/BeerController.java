package de.omb.ohmybeer.controller;

import de.omb.ohmybeer.entity.beer.Beer;
import de.omb.ohmybeer.entity.beer.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/beers", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerController {

    BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public List<Beer> getAll() {
        //TODO
        return null;
    }

    @GetMapping("/{id}")
    public Beer getOne(@PathVariable Long id) {
        //TODO
        return null;
    }

    @PostMapping
    public Beer addOne(Beer brewery) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        //TODO
    }

    @PutMapping
    public Beer updateOne() {
        //TODO
        return null;
    }
}
