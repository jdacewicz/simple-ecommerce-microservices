package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.order.dto.DetailedOrderDto;
import dev.jakubdacewicz.order_service.order.dto.OrderCreationRequest;
import dev.jakubdacewicz.order_service.order.dto.SummaryOrderDto;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    SummaryOrderDto getOrder(long id);

    DetailedOrderDto getDetailedOrder(long id);

    @Transactional
    DetailedOrderDto createOrder(OrderCreationRequest request);
}
