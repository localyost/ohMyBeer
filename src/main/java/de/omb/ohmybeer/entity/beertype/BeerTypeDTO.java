package de.omb.ohmybeer.entity.beertype;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import lombok.Getter;

@Getter
public class BeerTypeDTO extends AbstractDTO<BeerType> {

    private String name;

    protected BeerTypeDTO(BeerType entity) {
        super(entity);
    }

    @Override
    protected AbstractDTO<BeerType> setProperties() {
        name = entity.getName();
        return this;
    }
}
