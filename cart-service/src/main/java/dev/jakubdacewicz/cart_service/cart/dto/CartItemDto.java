package dev.jakubdacewicz.cart_service.cart.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CartItemDto(

        String id,

        String productId,

        BigDecimal price,

        int quantity,

        LocalDateTime updatedAt) {
}
