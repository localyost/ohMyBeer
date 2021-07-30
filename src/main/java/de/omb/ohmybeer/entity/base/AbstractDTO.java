package de.omb.ohmybeer.entity.base;

public abstract class AbstractDTO<Entity extends BaseEntity> {

    private Entity entity;

    protected AbstractDTO(Entity entity) {
        setFields(entity);
    }

    protected abstract void setFields(Entity entity);

}
