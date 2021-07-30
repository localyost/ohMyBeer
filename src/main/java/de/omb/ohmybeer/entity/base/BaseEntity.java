package de.omb.ohmybeer.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseEntity implements Serializable {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
