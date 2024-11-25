package dev.jakubdacewicz.product_service.stock.dto;

import java.math.BigDecimal;

public record StockUpdateRequest(

        BigDecimal price,

        int quantity) {
}
