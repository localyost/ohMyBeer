package de.omb.ohmybeer.entity.base;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractDTO<Entity extends BaseEntity> {

    @Getter
    private final long id;
    protected final Entity entity;
    private List<String> fetchSelectProps;

    protected AbstractDTO(Entity entity, String[] fetchProps) {
        this.entity = entity;
        this.id = entity.getId();
        if (fetchProps != null && fetchProps.length >= 1) {
            this.fetchSelectProps = Arrays.asList(fetchProps);
        }
        this.setProperties();
    }

    protected AbstractDTO(Entity entity) {
       this(entity, null);
    }

    protected void isFilterProperty(String propName, Consumer<Entity> lambda) {
        if (fetchSelectProps == null || fetchSelectProps.contains(propName)) {
            lambda.accept(this.entity);
        }
    }

    protected abstract void setProperties();

}
