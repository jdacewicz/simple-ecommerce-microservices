package dev.jakubdacewicz.product_service.stock.dto;

import java.math.BigDecimal;

public record StockCreationRequest(

        BigDecimal price,

        int quantity) {
}
