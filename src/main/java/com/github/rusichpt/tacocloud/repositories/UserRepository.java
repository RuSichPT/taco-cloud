package com.github.rusichpt.tacocloud.repositories;

import com.github.rusichpt.tacocloud.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
