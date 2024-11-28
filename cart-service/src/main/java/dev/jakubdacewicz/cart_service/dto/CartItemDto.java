package dev.jakubdacewicz.cart_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CartItemDto(

        String id,
        String productId,

        int quantity,
        BigDecimal price,

        LocalDateTime updatedAt) {
}
