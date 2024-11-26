package dev.jakubdacewicz.product_service.product.dto;

public record ProductCategoryUpdateResult(

        boolean categoryUpdated,
        boolean productUpdated) {
}
