package dev.jakubdacewicz.cart_service.cart.dto;

import java.math.BigDecimal;

public record CartItemDto(

        String productId,

        String name,

        int quantity,

        BigDecimal price) {
}
