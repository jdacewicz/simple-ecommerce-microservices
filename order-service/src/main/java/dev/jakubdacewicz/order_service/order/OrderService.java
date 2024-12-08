package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.order.dto.DetailedOrderDto;
import dev.jakubdacewicz.order_service.order.dto.OrderCreationRequest;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    @Transactional
    DetailedOrderDto createOrder(OrderCreationRequest request);
}
