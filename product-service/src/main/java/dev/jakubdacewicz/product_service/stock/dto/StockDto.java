package dev.jakubdacewicz.product_service.stock.dto;

import dev.jakubdacewicz.product_service.shared.types.StockStatus;

import java.math.BigDecimal;

public record StockDto(

        String id,

        int quantity,

        BigDecimal price,

        StockStatus status) {
}
