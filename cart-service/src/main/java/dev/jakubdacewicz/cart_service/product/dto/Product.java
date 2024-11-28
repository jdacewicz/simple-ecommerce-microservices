package dev.jakubdacewicz.cart_service.product.dto;

import java.math.BigDecimal;

public record Product(

        String id,

        BigDecimal price) {
}
