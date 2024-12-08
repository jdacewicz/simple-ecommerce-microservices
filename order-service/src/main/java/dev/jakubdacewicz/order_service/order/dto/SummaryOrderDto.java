package dev.jakubdacewicz.order_service.order.dto;

import dev.jakubdacewicz.order_service.order.MonetaryAmount;
import dev.jakubdacewicz.order_service.shared.types.OrderStatus;

import java.time.LocalDateTime;

public record SummaryOrderDto(

        Long id,

        OrderStatus status,

        MonetaryAmount totalMonetaryAmount,

        LocalDateTime createdAt) {
}
