package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity.base.AbstractDTO;
import de.omb.ohmybeer.entity.translation.Translation;
import lombok.Getter;

@Getter
public class IngredientDTO extends AbstractDTO<Ingredient> {

    private Translation labels;
    private String name;

    public IngredientDTO(Ingredient entity) {
        super(entity);
    }

    @Override
    protected void setProperties() {
        labels = entity.getLabels();
        name = entity.getName();
    }
}
