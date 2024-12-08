package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.order.dto.DetailedOrderDto;
import dev.jakubdacewicz.order_service.order.dto.OrderCreationRequest;
import dev.jakubdacewicz.order_service.order.dto.SummaryOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    SummaryOrderDto getOrder(long id);

    DetailedOrderDto getDetailedOrder(long id);

    Page<SummaryOrderDto> getOrders(int page, int size);

    @Transactional
    DetailedOrderDto createOrder(OrderCreationRequest request);
}
