package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.shared.types.OrderStatus;
import org.springframework.data.domain.Page;

interface OrderRepository {

    Order findById(long id);

    Page<Order> findAll(int page, int size);

    Order save(Order order);

    boolean updateStatus(long id, OrderStatus status);
}
