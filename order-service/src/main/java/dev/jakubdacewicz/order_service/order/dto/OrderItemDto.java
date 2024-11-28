package dev.jakubdacewicz.order_service.order.dto;

import dev.jakubdacewicz.order_service.order.MonetaryAmount;

public record OrderItemDto(

        Long id,

        String productId,

        String name,

        MonetaryAmount unitMonetaryAmount,
        MonetaryAmount totalMonetaryAmount) {
}
