package dev.jakubdacewicz.cart_service.dto;

import java.math.BigDecimal;

public record SummaryCartDto(

        String id,

        int totalQuantity,
        BigDecimal totalPrice) {
}
