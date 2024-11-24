package dev.jakubdacewicz.order_service.order.dto;

import dev.jakubdacewicz.order_service.order.MonetaryAmount;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedOrderDto(

        Long id,

        List<OrderItemDto> orderItems,

        String status,

        MonetaryAmount totalMonetaryAmount,

        LocalDateTime createdAt) {
}
