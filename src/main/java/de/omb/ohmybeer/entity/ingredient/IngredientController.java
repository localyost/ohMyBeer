package de.omb.ohmybeer.entity.ingredient;

import de.omb.ohmybeer.entity.base.AbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController extends AbstractController<Ingredient, IngredientRepository, IngredientService> {

    protected IngredientController(IngredientService service) { super(service); }

}
