package de.omb.entity.ingredient;

import de.omb.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class IngredientService extends GenericService<Ingredient, Long, IngredientRepository> {

    @Autowired
    public IngredientService(IngredientRepository repository) {
        super(repository);
    }

    public Ingredient findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public Set<Ingredient> findIngredientByName(String name) {
        return this.repository.findByNameStartsWithIgnoreCase(name);
    }
}
