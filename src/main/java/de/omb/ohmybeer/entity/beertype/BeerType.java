package de.omb.ohmybeer.entity.beertype;

import de.omb.ohmybeer.entity._base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class BeerType extends BaseEntity {

    @Column(nullable = false)
    private String name;

}
