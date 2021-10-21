package de.omb.ohmybeer.entity.resturanttype;

import de.omb.ohmybeer.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class RestaurantType extends BaseEntity {
    @Column(unique = true)
    @NotNull
    private String name;
}
