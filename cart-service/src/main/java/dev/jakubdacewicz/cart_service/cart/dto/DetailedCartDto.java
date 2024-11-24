package dev.jakubdacewicz.cart_service.cart.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedCartDto(

        String id,

        List<CartItemDto> cartItems,

        int totalQuantity,

        int totalPrice,

        LocalDateTime updatedAt) {
}
