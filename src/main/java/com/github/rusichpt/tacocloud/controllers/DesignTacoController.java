package com.github.rusichpt.tacocloud.controllers;

import com.github.rusichpt.tacocloud.models.Ingredient;
import com.github.rusichpt.tacocloud.models.Taco;
import com.github.rusichpt.tacocloud.models.TacoOrder;
import com.github.rusichpt.tacocloud.models.User;
import com.github.rusichpt.tacocloud.repositories.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.rusichpt.tacocloud.models.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final IngredientRepository repository;

    public DesignTacoController(IngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder,
                              @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            log.info(errors.getFieldErrors().toString());
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info(user.toString());
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }


    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = repository.findAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
        List<Ingredient> list = new ArrayList<>();

        ingredients.forEach(list::add);

        return list
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}