package dev.jakubdacewicz.product_service.product.dto;

import dev.jakubdacewicz.product_service.category.dto.SummaryCategoryDto;
import dev.jakubdacewicz.product_service.stock.dto.StockDto;

public record SummaryProductDto(

        String id,

        String name,

        String description,

        SummaryCategoryDto category,

        StockDto stock) {
}
