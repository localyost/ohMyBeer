package de.omb.webapi.controller.ingredient;

import de.omb.entity.ingredient.Ingredient;
import de.omb.entity.ingredient.IngredientRepository;
import de.omb.entity.ingredient.IngredientService;
import de.omb.webapi.controller.base.AbstractController;
import de.omb.webapi.controller.base.AbstractDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController extends AbstractController<Ingredient, IngredientRepository, IngredientService> {

    protected IngredientController(IngredientService service) { super(service); }

    @Override
    protected AbstractDTO<Ingredient> createDTO(Ingredient entity, String[] fetchProps) {
        return new IngredientDTO(entity);
    }

    @GetMapping("/search")
    public Set<IngredientDTO> findIngredientByName(@RequestParam String q) {
        return service.findIngredientByName(q)
                .stream()
                .map(IngredientDTO::new)
                .collect(Collectors.toSet());
    }

}
