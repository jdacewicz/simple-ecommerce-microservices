package dev.jakubdacewicz.order_service.order.dto;

import dev.jakubdacewicz.order_service.order.MonetaryAmount;
import dev.jakubdacewicz.order_service.shared.types.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedOrderDto(

        Long id,

        List<OrderItemDto> orderItems,

        OrderStatus status,

        MonetaryAmount orderMonetaryAmount,

        LocalDateTime createdAt) {
}
