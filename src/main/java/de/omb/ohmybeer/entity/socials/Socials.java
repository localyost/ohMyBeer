package de.omb.ohmybeer.entity.socials;

import de.omb.ohmybeer.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Socials extends BaseEntity {

    @Column
    private String website;
    @Column
    private String facebook;
    @Column
    private String instagram;
    @Column
    private String twitter;
}
