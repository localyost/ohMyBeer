package de.omb.entity.translation;

import de.omb.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Translation extends BaseEntity {

    @Column(columnDefinition = "text")
    private String de;

    @Column(columnDefinition = "text")
    private String en;

    @Column(columnDefinition = "text")
    private String cz;
}
