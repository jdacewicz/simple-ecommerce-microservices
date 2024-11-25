package dev.jakubdacewicz.product_service.category.dto;

import java.time.LocalDateTime;

public record DetailedCategoryDto(

        String id,

        String name,
        String description,

        LocalDateTime createdAt,
        LocalDateTime updatedAt,

        int productCount,

        boolean enabled) {
}
