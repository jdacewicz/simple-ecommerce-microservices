package dev.jakubdacewicz.product_service.stock.dto;

import java.math.BigDecimal;

public record StockDto(

        String id,

        String productId,

        int quantity,

        BigDecimal price) {
}
