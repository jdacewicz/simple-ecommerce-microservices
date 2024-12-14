package dev.jakubdacewicz.order_service.cart.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record Cart(

        String id,

        List<CartItem> cartItems,

        int totalQuantity,

        BigDecimal totalPrice,

        LocalDateTime updatedAt) {

    public static Cart defaultCart() {
        return new Cart(null,
                Collections.emptyList(),
                0,
                BigDecimal.ZERO,
                LocalDateTime.now());
    }
}
