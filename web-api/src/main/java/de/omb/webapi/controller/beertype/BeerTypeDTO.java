package de.omb.webapi.controller.beertype;

import de.omb.entity.beertype.BeerType;
import de.omb.webapi.controller.base.AbstractDTO;
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
