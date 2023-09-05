package com.github.rusichpt.tacocloud.repositories;

import com.github.rusichpt.tacocloud.models.TacoOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween
            (String deliveryZip, Date startDate, Date endDate);

    List<TacoOrder> findByDeliveryZipAndDeliveryCityIgnoreCase(
            String deliveryTo, String deliveryCity);

    @Query(value = "select o from TacoOrder o where o.deliveryCity='Seattle'")
    List<TacoOrder> readOrdersDeliveredInSeattle();
}
