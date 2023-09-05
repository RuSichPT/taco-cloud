package com.github.rusichpt.tacocloud.components;

import com.github.rusichpt.tacocloud.models.Ingredient;
import com.github.rusichpt.tacocloud.repositories.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository repository;

    public IngredientByIdConverter(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ingredient convert(String id) {
        return repository.findById(id).orElse(null);
    }
}

