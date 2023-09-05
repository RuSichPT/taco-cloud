package com.github.rusichpt.tacocloud.repositories;

import com.github.rusichpt.tacocloud.models.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
