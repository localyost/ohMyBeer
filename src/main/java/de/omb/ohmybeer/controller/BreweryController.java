package de.omb.ohmybeer.controller;

import de.omb.ohmybeer.entity.brewery.Brewery;
import de.omb.ohmybeer.entity.brewery.BreweryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/breweries", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreweryController {

    BreweryService breweryService;

    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping
    public List<Brewery> getAll() {
        //TODO
        return null;
    }

    @GetMapping("/{id}")
    public Brewery getOne(@PathVariable Long id) {
        //TODO
        return null;
    }

    @PostMapping
    public Brewery addOne(Brewery brewery) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        //TODO
    }

    @PutMapping
    public Brewery updateOne() {
        //TODO
        return null;
    }
}
