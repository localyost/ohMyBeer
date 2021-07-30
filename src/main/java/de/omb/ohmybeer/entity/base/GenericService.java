package de.omb.ohmybeer.entity.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public abstract class GenericService<Entity extends BaseEntity, ID extends Serializable, Repository extends JpaRepository<Entity, ID>> {

    protected final Repository repository;

    public GenericService(Repository repository) {
        this.repository = repository;
    }

    public List<Entity> create(Set<Entity> entities) {
        return repository.saveAll(entities);
    }
    public Entity create(Entity entity) { return repository.save(entity); }

    public List<Entity> save(Set<Entity> entities) { return repository.saveAll(entities); }
    public Entity save(Entity entity) { return repository.save(entity); }

    public List<Entity> getAll() {
        return repository.findAll();
    }

    public Entity getOne(ID id) {
        return repository.getById(id);
    }

    public void delete(Set<ID> ids) { repository.deleteAllByIdInBatch(ids); }
    public void delete(ID id) { repository.deleteById(id); }
}
