package dev.jakubdacewicz.product_service.product.dto;

import dev.jakubdacewicz.product_service.category.dto.SummaryCategoryDto;

import java.math.BigDecimal;

public record SummaryProductDto(

        String id,

        String name,

        String description,

        SummaryCategoryDto category,

        BigDecimal price,

        String status) {
}
