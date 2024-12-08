package dev.jakubdacewicz.order_service.cart.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CartItem(

        String id,
        String productId,

        int quantity,

        BigDecimal price,

        LocalDateTime updatedAt) {
}
