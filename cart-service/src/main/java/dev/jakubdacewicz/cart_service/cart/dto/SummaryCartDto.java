package dev.jakubdacewicz.cart_service.cart.dto;

import java.math.BigDecimal;

public record SummaryCartDto(

        int totalQuantity,

        BigDecimal totalPrice) {
}
