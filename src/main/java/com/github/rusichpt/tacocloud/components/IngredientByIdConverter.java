package com.github.rusichpt.tacocloud.components;

import com.github.rusichpt.tacocloud.models.Ingredient;
import com.github.rusichpt.tacocloud.models.IngredientRef;
import com.github.rusichpt.tacocloud.repositories.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientRef> {

    private final IngredientRepository repository;

    public IngredientByIdConverter(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public IngredientRef convert(String id) {
        Optional<Ingredient> byId = repository.findById(id);
        if (byId.isPresent()){
            Ingredient ingredient = byId.get();
            return new IngredientRef(ingredient.getId());
        }
        return null;
    }
}

