package de.omb.ohmybeer.entity.base;

import lombok.Getter;

public abstract class AbstractDTO<Entity extends BaseEntity> {

    protected final Entity entity;
    @Getter
    private final long id;

    protected AbstractDTO(Entity entity) {
        this.entity = entity;
        this.id = entity.getId();
    }

    protected abstract AbstractDTO<Entity> setProperties();

}
