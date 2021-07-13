package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.BaseEntity;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class Beer extends BaseEntity {

    //TODO Food Pairing
    //TODO Stammwürze
    //TODO Color
    //TODO Beer type
    //TODO Brew type

    @Column
    private String name;
    @ElementCollection
    private Set<String> photos;
    @ElementCollection
    private Map<Language, String> information;
    @Column(length = 120)
    private int ibu;
    @Column
    private double alcoholContent;
}
