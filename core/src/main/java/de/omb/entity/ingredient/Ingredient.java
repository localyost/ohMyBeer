package de.omb.entity.ingredient;

import de.omb.entity.base.BaseEntity;
import de.omb.entity.translation.Translation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Ingredient extends BaseEntity {

    @OneToOne
    private Translation labels;

    @Column(unique = true)
    @NotNull
    private String name;

}
