package dev.jakubdacewicz.product_service.product.dto;

import java.math.BigDecimal;

public record ProductCreationRequest(

        String name,

        String description,

        String categoryId,

        BigDecimal price,

        int quantity
) {
}
