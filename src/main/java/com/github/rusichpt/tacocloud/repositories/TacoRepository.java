package com.github.rusichpt.tacocloud.repositories;

import com.github.rusichpt.tacocloud.models.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
