package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity._base.BaseEntity;
import de.omb.ohmybeer.enums.Language;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Map;

@Entity
public class Ingredient extends BaseEntity {

    @ElementCollection
    private Map<Language, String> name;

}
