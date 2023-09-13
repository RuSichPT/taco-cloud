package com.github.rusichpt.tacocloud.client;

import com.github.rusichpt.tacocloud.models.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TacoCloudClient {
    private final RestTemplate rest;
    private final String URL = "http://localhost:8081/data-api/ingredients/";
    private final String URLid = URL + "{id}";

    public TacoCloudClient(RestTemplate rest) {
        this.rest = rest;
    }

    public Ingredient getIngredientById1(String ingredientId) {
        return rest.getForObject(URLid, Ingredient.class, ingredientId);
    }

    public Ingredient getIngredientById2(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return rest.getForObject(URLid, Ingredient.class, urlVariables);
    }

    public Ingredient getIngredientById3(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI url = UriComponentsBuilder
                .fromHttpUrl(URLid)
                .build(urlVariables);
        return rest.getForObject(url, Ingredient.class);
    }

    public Ingredient getIngredientById4(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity =
                rest.getForEntity(URLid, Ingredient.class, ingredientId);
        log.info("Fetched time: {}",
                responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        rest.put(URLid, ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        rest.delete(URLid, ingredient.getId());
    }

    public Ingredient createIngredient1(Ingredient ingredient) {
        return rest.postForObject(URL, ingredient, Ingredient.class);
    }

    public java.net.URI createIngredient2(Ingredient ingredient) {
        return rest.postForLocation(URL, ingredient);
    }

    public Ingredient createIngredient3(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity =
                rest.postForEntity(URL, ingredient, Ingredient.class);
        log.info("New resource created at {}",
                responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

}
