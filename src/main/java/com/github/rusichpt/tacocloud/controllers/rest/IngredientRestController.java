package com.github.rusichpt.tacocloud.controllers.rest;

import com.github.rusichpt.tacocloud.models.Ingredient;
import com.github.rusichpt.tacocloud.repositories.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ingredients",
        produces = "application/json")
@CrossOrigin(origins = "http://localhost:8081")
public class IngredientRestController {
    private final IngredientRepository repo;

    public IngredientRestController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        repo.deleteById(ingredientId);
    }
}
