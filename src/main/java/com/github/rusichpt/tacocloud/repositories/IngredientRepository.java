package com.github.rusichpt.tacocloud.repositories;

import com.github.rusichpt.tacocloud.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
