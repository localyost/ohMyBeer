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

    public Ingredient getByName(String name) {
        return repository.getByName(name);
    }

    public Ingredient findOrCreate(String name) {
        Ingredient ingredient = repository.getByName(name);
        if (ingredient == null) {
            ingredient = new Ingredient();
            ingredient.setName(name);
            return repository.save(ingredient);
        }
        return ingredient;
    }
}
