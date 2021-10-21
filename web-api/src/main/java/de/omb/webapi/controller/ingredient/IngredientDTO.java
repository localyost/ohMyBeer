package de.omb.webapi.controller.ingredient;

import de.omb.entity.ingredient.Ingredient;
import de.omb.entity.translation.Translation;
import de.omb.webapi.controller.base.AbstractDTO;
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
