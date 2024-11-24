package dev.jakubdacewicz.order_service.order.controller;

import dev.jakubdacewicz.order_service.order.dto.DetailedOrderDto;
import dev.jakubdacewicz.order_service.order.dto.OrderCreationRequest;
import dev.jakubdacewicz.order_service.order.dto.OrderStatusUpdateResult;
import dev.jakubdacewicz.order_service.order.dto.SummaryOrderDto;
import dev.jakubdacewicz.order_service.shared.types.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestControllerV1 {

    @GetMapping("/{id}")
    public SummaryOrderDto getOrder(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{id}/details")
    public DetailedOrderDto getOrderDetails(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public Page<SummaryOrderDto> getOrders(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public DetailedOrderDto createOrder(@RequestBody OrderCreationRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}/status/{status}")
    public OrderStatusUpdateResult updateOrderStatus(@PathVariable String id,
                                                     @PathVariable OrderStatus status) {
        throw new UnsupportedOperationException();
    }
}
