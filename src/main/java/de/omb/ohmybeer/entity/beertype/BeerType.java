package de.omb.ohmybeer.entity.beertype;

import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.enums.Language;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Map;

@Entity
public class BeerType extends BaseEntity {

    @ElementCollection
    private Map<Language, String> name;

}
