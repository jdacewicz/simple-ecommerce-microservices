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

        LocalDateTime createdAt,

        LocalDateTime updatedAt) {

    public static class Builder {

        private String id;
        private String name;
        private String description;

        private SummaryCategoryDto category;
        private StockDto stock;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder category(SummaryCategoryDto category) {
            this.category = category;
            return this;
        }

        public Builder stock(StockDto stock) {
            this.stock = stock;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public DetailedProductDto build() {
            return new DetailedProductDto(id, name, description, category, stock, createdAt, updatedAt);
        }
    }
}
