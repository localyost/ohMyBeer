package de.omb.ohmybeer.entity._base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class GenericService<Entity extends BaseEntity, ID extends Serializable, Repository extends JpaRepository<Entity, ID>> {

    protected final Repository repository;

    public GenericService(Repository repository) {
        this.repository = repository;
    }

    public Entity create(Entity entity) {
        return repository.save(entity);
    }

    public Entity save(Entity entity) { return repository.save(entity); }

    public List<Entity> getAll() {
        return repository.findAll();
    }

    public Entity getOne(ID id) {
        return repository.getById(id);
    }

    public void deleteOne(ID id) {
        repository.deleteById(id);
    }
}
