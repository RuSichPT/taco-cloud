package com.github.rusichpt.tacocloud.repositories;

import com.github.rusichpt.tacocloud.models.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
