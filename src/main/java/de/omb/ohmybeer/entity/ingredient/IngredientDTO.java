package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.enums.Language;
import lombok.Getter;

import java.util.Map;

@Getter
public class IngredientDTO extends AbstractDTO<Ingredient> {

    private Map<Language, String> labels;

    protected IngredientDTO(Ingredient entity) {
        super(entity);
    }

    @Override
    protected void setFields(Ingredient entity) {
        labels = entity.getLabels();
    }
}
