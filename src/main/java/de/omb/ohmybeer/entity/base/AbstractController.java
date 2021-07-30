package de.omb.ohmybeer.entity.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractController<Entity extends BaseEntity, Repository extends JpaRepository<Entity, Long>, Service extends GenericService<Entity, Long, Repository>> {
    protected Service service;

    protected AbstractController(Service service) {
        this.service = service;
    }

    protected abstract AbstractDTO<Entity> createDTO(Entity entity);

    @GetMapping
    public List<AbstractDTO<Entity>> getAll(@RequestParam int start, @RequestParam int end) {
        return onGetAll(start, end);
    }

    @GetMapping("/{id}")
    public AbstractDTO<Entity> getOne(@PathVariable Long id) {
        return onGetOne(id);
    }

    @PostMapping
    public AbstractDTO<Entity> createOne(Entity entity) {
        return onCreateOne(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        onDelete(Set.of(id));
    }

    @PutMapping
    public AbstractDTO<Entity> updateOne(Entity entity) {
        return onUpdate(entity);
    }

    protected List<AbstractDTO<Entity>> onGetAll(int start, int end) {
        boolean hasParams = start + end > 0;
        List<Entity> entities = hasParams ?  service.getAll(start, end) : service.getAll();
        return entities.stream().map(this::createDTO).collect(Collectors.toList());
    }
    protected AbstractDTO<Entity> onGetOne(Long id) {
        return createDTO(service.getOne(id));
    }

    protected AbstractDTO<Entity> onCreateOne(Entity entity) {
        return createDTO(service.create(entity));
    }

    protected void onDelete(Set<Long> ids) {
        service.delete(ids);
    }

    protected AbstractDTO<Entity> onUpdate(Entity entity) {
        return createDTO(service.save(entity));
    }
}
