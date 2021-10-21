package de.omb.entity.beertype;

import de.omb.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class BeerType extends BaseEntity {

    @Column(unique = true)
    @NotNull
    private String name;

}
