package de.omb.ohmybeer.entity.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByNameIgnoreCase(String name);

    Set<Ingredient> findByNameStartsWithIgnoreCase(String name);
}
