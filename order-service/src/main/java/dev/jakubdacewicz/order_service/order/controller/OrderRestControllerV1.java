package dev.jakubdacewicz.order_service.order.controller;

import dev.jakubdacewicz.order_service.order.dto.DetailedOrderDto;
import dev.jakubdacewicz.order_service.order.dto.OrderCreationRequest;
import dev.jakubdacewicz.order_service.order.dto.OrderStatusUpdateResult;
import dev.jakubdacewicz.order_service.order.dto.SummaryOrderDto;
import dev.jakubdacewicz.order_service.shared.types.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/orders")
public class OrderRestControllerV1 {

    @GetMapping("/{id}")
    public SummaryOrderDto getOrder(@Positive @PathVariable long id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{id}/details")
    public DetailedOrderDto getOrderDetails(@Positive @PathVariable long id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public Page<SummaryOrderDto> getOrders(@PositiveOrZero @RequestParam(defaultValue = "0") int page,
                                           @Positive @RequestParam(defaultValue = "10") int size) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public DetailedOrderDto createOrder(@Valid @RequestBody OrderCreationRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}/status/{status}")
    public OrderStatusUpdateResult updateOrderStatus(@Positive @PathVariable long id,
                                                     @NotNull @PathVariable OrderStatus status) {
        throw new UnsupportedOperationException();
    }
}
