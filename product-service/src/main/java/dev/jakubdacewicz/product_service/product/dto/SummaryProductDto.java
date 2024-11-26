package dev.jakubdacewicz.product_service.product.dto;

import dev.jakubdacewicz.product_service.stock.dto.StockDto;

public record SummaryProductDto(

        String id,

        String name,

        String description,

        StockDto stock) {
}
