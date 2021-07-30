package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.enums.Language;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class Ingredient extends BaseEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Language, String> labels;
    @Column
    @NotNull
    private String name;

    public void addLabel(Language language, String label) {
        if (this.labels == null) { this.labels = new HashMap<>(); }
        this.labels.put(language, label);
    }

}
