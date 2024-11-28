package dev.jakubdacewicz.order_service.order.dto;

import dev.jakubdacewicz.order_service.order.MonetaryAmount;

import java.time.LocalDateTime;

public record SummaryOrderDto(

        Long id,

        String status,

        MonetaryAmount totalMonetaryAmount,

        LocalDateTime createdAt) {
}
