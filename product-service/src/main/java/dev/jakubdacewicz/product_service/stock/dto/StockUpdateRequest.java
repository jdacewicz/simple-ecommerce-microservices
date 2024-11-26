package dev.jakubdacewicz.product_service.stock.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record StockUpdateRequest(

        @NotNull
        BigDecimal price,

        @PositiveOrZero
        int quantity) {
}
