package com.github.rusichpt.tacocloud.client;

import com.github.rusichpt.tacocloud.models.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TacoCloudClientTest {
    private final TacoCloudClient client;

    @Autowired
    public TacoCloudClientTest(TacoCloudClient tacoCloudClient) {
        this.client = tacoCloudClient;
    }

    @Test
    void shouldProperlyGetIngredientById() {
        Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
        Ingredient ingredient = client.getIngredientById1(groundBeef.getId());
        Assertions.assertEquals(ingredient.getName(), groundBeef.getName());

    }
}
