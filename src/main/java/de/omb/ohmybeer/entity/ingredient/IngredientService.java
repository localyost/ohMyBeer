package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class IngredientService extends GenericService<Ingredient, Long, IngredientRepository> {

    @Autowired
    public IngredientService(IngredientRepository repository) {
        super(repository);
    }

    public Ingredient getByName(String name) {
        return repository.getByName(name);
    }

    public Set<Ingredient> findIngredientByName(String name) {
        return this.repository.findByNameStartsWithIgnoreCase(name);
    }
}
