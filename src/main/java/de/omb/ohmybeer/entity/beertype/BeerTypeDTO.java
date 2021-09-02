package de.omb.ohmybeer.entity.beertype;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import lombok.Getter;

@Getter
public class BeerTypeDTO extends AbstractDTO<BeerType> {

    private String name;

    public BeerTypeDTO(BeerType entity) {
        super(entity);
    }

    @Override
    protected void setProperties() {
        name = entity.getName();
    }
}
