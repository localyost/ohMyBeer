package de.omb.ohmybeer.entity.brewery;

import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.entity.beer.Beer;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BreweryDTO extends AbstractDTO<Brewery> {

    private String name;
    private String logo;
    private Address address;
    private Socials socials;
    private Set<String> photos;
    private Map<Language, String> information;
    private Set<Long> beerIds;

    public BreweryDTO(Brewery entity, String[] fetchProps) {
        super(entity, fetchProps);
    }

    @Override
    protected void setProperties() {
        this.isFilterProperty("name", brewery -> this.name = brewery.getName());
        this.isFilterProperty("logo", brewery -> this.logo = brewery.getLogo());
        this.isFilterProperty("address", brewery -> this.address = brewery.getAddress());
        this.isFilterProperty("socials", brewery -> this.socials = brewery.getSocials());
        this.isFilterProperty("photos", brewery -> this.photos = brewery.getPhotos());
        this.isFilterProperty("information", brewery -> this.information = brewery.getInformation());
        this.isFilterProperty("beerIds", brewery -> this.beerIds = brewery.getBeers().stream().map(Beer::getId).collect(Collectors.toSet()));
    }
}
