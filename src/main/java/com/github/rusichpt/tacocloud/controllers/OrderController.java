package com.github.rusichpt.tacocloud.controllers;

import com.github.rusichpt.tacocloud.components.OrderProps;
import com.github.rusichpt.tacocloud.models.TacoOrder;
import com.github.rusichpt.tacocloud.repositories.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private final OrderProps orderProps;

    private final OrderRepository repository;

    public OrderController(OrderProps orderProps, OrderRepository repository) {
        this.orderProps = orderProps;
        this.repository = repository;
    }

    @GetMapping
    public String ordersForUser(Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize(), Sort.by("placedAt"));
        model.addAttribute("orders", repository.findAll(pageable));
        log.info("Page size: {}", orderProps.getPageSize());
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            log.info(errors.getFieldErrors().toString());
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        repository.save(order);
        sessionStatus.setComplete();

        return "redirect:/orders";
    }

}

