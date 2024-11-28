package dev.jakubdacewicz.cart_service.cart.dto;

import java.math.BigDecimal;

public record SummaryCartDto(

        String id,

        int totalQuantity,
        BigDecimal totalPrice) {
}
