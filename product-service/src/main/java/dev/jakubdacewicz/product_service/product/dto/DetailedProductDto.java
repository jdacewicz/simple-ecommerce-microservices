package dev.jakubdacewicz.product_service.product.dto;

import dev.jakubdacewicz.product_service.category.dto.SummaryCategoryDto;
import dev.jakubdacewicz.product_service.stock.dto.StockDto;

import java.time.LocalDateTime;

public record DetailedProductDto(

        String id,

        String name,

        String description,

        SummaryCategoryDto category,

        StockDto stock,

        String status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt) {
}
