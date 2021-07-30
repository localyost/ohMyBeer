package de.omb.ohmybeer.entity.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public abstract class AbstractController<Entity extends BaseEntity, Repository extends JpaRepository<Entity, Long>, Service extends GenericService<Entity, Long, Repository>> {

    protected AbstractController(Service service) {
        this.service = service;
    }
    protected Service service;

    @GetMapping
    public List<Entity> getAll() {
        return onGetAll();
    }

    @GetMapping("/{id}")
    public Entity getOne(@PathVariable Long id) {
        return onGetOne(id);
    }

    @PostMapping
    public Entity createOne(Entity entity) {
        return onCreateOne(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        onDelete(Set.of(id));
    }

    @PutMapping
    public Entity updateOne(Entity entity) {
        return onUpdate(entity);
    }

    protected List<Entity> onGetAll() {
        return service.getAll();
    }
    protected Entity onGetOne(Long id) {
        return service.getOne(id);
    }

    protected Entity onCreateOne(Entity entity) {
        return service.create(entity);
    }

    protected void onDelete(Set<Long> ids) {
        service.delete(ids);
    }

    protected Entity onUpdate(Entity entity) {
        return service.save(entity);
    }
}
