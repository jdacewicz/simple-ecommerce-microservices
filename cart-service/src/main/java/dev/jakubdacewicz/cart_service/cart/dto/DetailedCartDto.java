package dev.jakubdacewicz.cart_service.cart.dto;

import java.math.BigDecimal;
import java.util.Set;

public record DetailedCartDto(

        Set<CartItemDto> cartItems,

        int totalQuantity,

        BigDecimal totalPrice) {
}
