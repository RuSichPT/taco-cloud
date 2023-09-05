package com.github.rusichpt.tacocloud.repositories;

import com.github.rusichpt.tacocloud.models.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
