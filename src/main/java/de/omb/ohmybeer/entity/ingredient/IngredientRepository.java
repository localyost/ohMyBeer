package de.omb.ohmybeer.entity.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("select i from Ingredient as i where i.name = :name")
    Ingredient getByName(@Param("name") String name);
}
