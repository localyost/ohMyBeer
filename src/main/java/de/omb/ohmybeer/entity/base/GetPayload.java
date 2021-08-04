package de.omb.ohmybeer.entity.base;

import lombok.Getter;

import java.util.List;

@Getter
public class GetPayload<Entity extends BaseEntity> {
    private final Iterable<AbstractDTO<Entity>> content;
    private final long total;

    public GetPayload(List<AbstractDTO<Entity>> content, long total) {
        this.total = total;
        this.content = content;
    }
}
