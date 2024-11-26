package dev.jakubdacewicz.product_service.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreationRequest(

        @NotBlank
        @Size(max = 64)
        String name,

        @NotNull
        @Size(max = 255)
        String description,

        String categoryId,

        @NotNull
        BigDecimal price,

        @PositiveOrZero
        int quantity
) {
}
