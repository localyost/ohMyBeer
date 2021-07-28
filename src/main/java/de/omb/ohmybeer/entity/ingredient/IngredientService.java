package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity._base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService extends GenericService<Ingredient, Long, IngredientRepository> {

    @Autowired
    public IngredientService(IngredientRepository repository) {
        super(repository);
    }
}
