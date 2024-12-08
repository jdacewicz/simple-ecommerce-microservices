package dev.jakubdacewicz.order_service.order;

import org.springframework.data.domain.Page;

interface OrderRepository {

    Order findById(long id);

    Page<Order> findAll(int page, int size);

    Order save(Order order);
}
