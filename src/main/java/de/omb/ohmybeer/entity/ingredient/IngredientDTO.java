package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;

import java.util.Map;

@Getter
public class IngredientDTO extends AbstractDTO<Ingredient> {

    private Map<Language, String> labels;
    private String name;

    protected IngredientDTO(Ingredient entity) {
        super(entity);
    }

    @Override
    protected void setProperties() {
        labels = entity.getLabels();
        name = entity.getName();
    }
}
