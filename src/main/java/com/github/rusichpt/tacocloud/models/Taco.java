package com.github.rusichpt.tacocloud.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    public List<IngredientRef> getIngredientRefs() {
        List<IngredientRef> list = new ArrayList<>();
        for (Ingredient i : ingredients) {
            list.add(new IngredientRef(i.getId()));
        }
        return list;
    }
}
