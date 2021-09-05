package de.omb.ohmybeer.entity.translation;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import lombok.Getter;

@Getter
public class TranslationDTO extends AbstractDTO<Translation> {

    private String de;
    private String en;
    private String cz;

    public TranslationDTO(Translation entity) {
        super(entity);
    }

    @Override
    protected void setProperties() {
        this.de = entity.getDe();
        this.en = entity.getEn();
        this.cz = entity.getCz();
    }
}
