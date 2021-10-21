package de.omb.webapi.controller.brewery;

import de.omb.entity.address.Address;
import de.omb.entity.beer.Beer;
import de.omb.entity.brewery.Brewery;
import de.omb.entity.socials.Socials;
import de.omb.entity.translation.Translation;
import de.omb.webapi.controller.base.AbstractDTO;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BreweryDTO extends AbstractDTO<Brewery> {

    private String name;
    private String legalEntity;
    private String logo;
    private Address address;
    private Socials socials;
    private Set<String> photos;
    private Translation information;
    private Set<Long> beerIds;

    public BreweryDTO(Brewery entity, String... fetchProps) {
        super(entity, fetchProps);
    }

    @Override
    protected void setProperties() {
        this.isFilterProperty("name", brewery -> this.name = brewery.getName());
        this.isFilterProperty("legalEntity", brewery -> this.legalEntity = brewery.getLegalEntity());
        this.isFilterProperty("logo", brewery -> this.logo = brewery.getLogo());
        this.isFilterProperty("address", brewery -> this.address = brewery.getAddress());
        this.isFilterProperty("socials", brewery -> this.socials = brewery.getSocials());
        this.isFilterProperty("photos", brewery -> this.photos = brewery.getPhotos());
        this.isFilterProperty("information", brewery -> this.information = brewery.getInformation());
        this.isFilterProperty("beerIds", brewery -> this.beerIds = brewery.getBeers().stream().map(Beer::getId).collect(Collectors.toSet()));
    }
}
