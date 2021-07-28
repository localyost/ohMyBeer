package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity._base.BaseEntity;
import de.omb.ohmybeer.enums.Language;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class Ingredient extends BaseEntity {

    @ElementCollection
    private Map<Language, String> label;
    @Column
    private String name;

    public void addLabel(Language language, String label) {
        if (this.label == null) { this.label = new HashMap<>(); }
        this.label.put(language, label);
    }

}
