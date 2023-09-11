package com.github.rusichpt.tacocloud.configs;

import com.github.rusichpt.tacocloud.models.Ingredient;
import com.github.rusichpt.tacocloud.models.User;
import com.github.rusichpt.tacocloud.repositories.IngredientRepository;
import com.github.rusichpt.tacocloud.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.github.rusichpt.tacocloud.models.Ingredient.Type;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }

    @Bean
    @Profile("!prod")
    public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
            userRepository.save(new User("user", encoder.encode("1"), "Pavel",
                    "1", "vrn", "1", "1", "1"));
            User admin = new User("admin", encoder.encode("2"), "Admin",
                    "1", "vrn", "1", "1", "1");
            admin.getRoles().add("ROLE_ADMIN");
            userRepository.save(admin);
        };
    }
}
