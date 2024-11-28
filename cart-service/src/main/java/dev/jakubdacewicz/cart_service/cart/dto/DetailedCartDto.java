package dev.jakubdacewicz.cart_service.cart.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DetailedCartDto(

        String id,

        List<CartItemDto> cartItems,

        int totalQuantity,
        BigDecimal totalPrice,

        LocalDateTime updatedAt) {
}
