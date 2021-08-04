package de.omb.ohmybeer.entity.base;

import org.springframework.data.domain.Page;
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
    public GetPayload<Entity> fetchMany(@RequestParam int page, @RequestParam int size) {
        return onFetchMany(page, size);
    }

    @GetMapping("/{id}")
    public AbstractDTO<Entity> fetchOne(@PathVariable Long id) {
        return onFetchOne(id);
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

    protected GetPayload<Entity> onFetchMany(int page, int size) {
        Page<Entity> pageResponse = service.getAll(page, size);
        List<AbstractDTO<Entity>> content = pageResponse.stream().map(this::buildFetchManyDTO).collect(Collectors.toList());
        return new GetPayload<>(content, pageResponse.getTotalElements());
    }
    protected AbstractDTO<Entity> onFetchOne(Long id) {
        return buildFetchOneDTO(service.getOne(id));
    }

    protected AbstractDTO<Entity> onCreateOne(Entity entity) {
        return buildCreateOneDTO(service.create(entity));
    }

    protected void onDelete(Set<Long> ids) {
        service.delete(ids);
    }

    protected AbstractDTO<Entity> onUpdate(Entity entity) {
        return createDTO(service.save(entity));
    }

    protected AbstractDTO<Entity> buildFetchManyDTO(Entity entity) { return this.createDTO(entity); }

    protected AbstractDTO<Entity> buildFetchOneDTO(Entity entity) { return this.createDTO(entity); }

    protected AbstractDTO<Entity> buildCreateOneDTO(Entity entity) { return this.createDTO(entity); }
}
