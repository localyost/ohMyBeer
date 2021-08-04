package de.omb.ohmybeer.entity.base;

public abstract class AbstractDTO<Entity extends BaseEntity> {

    protected final Entity entity;

    protected AbstractDTO(Entity entity) {
        this.entity = entity;
    }

    protected abstract AbstractDTO<Entity> setProperties();

}
