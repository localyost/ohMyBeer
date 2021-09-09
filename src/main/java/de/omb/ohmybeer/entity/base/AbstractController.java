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

    protected abstract AbstractDTO<Entity> createDTO(Entity entity, String[] fetchProps);
    private AbstractDTO<Entity> getDTO(Entity entity, String[] fetchProps) {return this.createDTO(entity, fetchProps);}
    private AbstractDTO<Entity> getDTO(Entity entity) {  return getDTO(entity, null);}

    /*  End Points  */

    @GetMapping
    public GetPayload<Entity> fetchMany (
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String[] props
    ) {
        return onFetchMany(page, size, props);
    }

    @GetMapping("/{id}")
    public AbstractDTO<Entity> fetchOne (
            @PathVariable Long id,
            @RequestParam(required = false) String[] props
    ) {
        return onFetchOne(id, props);
    }

    @PostMapping
    public AbstractDTO<Entity> createOne(@RequestBody Entity entity) {
        return onCreateOne(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        onDelete(Set.of(id));
    }

    @PutMapping
    public AbstractDTO<Entity> updateOne(@RequestBody Entity entity) {
        return onUpdate(entity);
    }

    /*  Protected Methods  */

    protected GetPayload<Entity> onFetchMany(int page, int size, String[] fetchProps) {
        Page<Entity> pageResponse = service.getAll(page, size);
        List<AbstractDTO<Entity>> content = pageResponse
                .stream()
                .map(entity -> this.getDTO(entity, fetchProps))
                .collect(Collectors.toList());
        return new GetPayload<>(content, pageResponse.getTotalElements());
    }

    protected AbstractDTO<Entity> onFetchOne(Long id, String[] fetchProps) {
        return getDTO(service.getOne(id), fetchProps);
    }

    protected AbstractDTO<Entity> onCreateOne(Entity entity) {
        return getDTO(service.create(entity));
    }

    protected void onDelete(Set<Long> ids) {
        service.delete(ids);
    }

    protected AbstractDTO<Entity> onUpdate(Entity entity) {
        return getDTO(service.save(entity));
    }
}
