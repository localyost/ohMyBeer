package de.omb.ohmybeer.entity.brewery;

import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class BreweryDTO extends AbstractDTO<Brewery> {

    private String name;
    private String logo;
    private Address address;
    private Socials socials;
    private Set<String> photos;
    private Map<Language, String> information;
    private Set<Long> beerIds;

    public BreweryDTO(Brewery entity) {
        super(entity);
    }

    @Override
    protected void setFields(Brewery entity) {
        name = entity.getName();
        logo = entity.getLogo();
        address = entity.getAddress();
        socials = entity.getSocials();
        photos = entity.getPhotos();
        information = entity.getInformation();
//        beerIds = entity.getBeers().stream().map(Beer::getId).collect(Collectors.toSet());
    }
}
