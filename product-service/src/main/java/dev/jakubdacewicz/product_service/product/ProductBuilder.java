package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.category.Category;
import dev.jakubdacewicz.product_service.shared.types.ProductStatus;
import dev.jakubdacewicz.product_service.stock.Stock;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductBuilder {

    private String id;

    private String name;
    private String description = "";

    private Stock stock;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Category category;

    private ProductStatus status = ProductStatus.NOT_AVAILABLE;

    private UUID businessKey = UUID.randomUUID();

    public ProductBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder stock(Stock stock) {
        this.stock = stock;
        return this;
    }

    public ProductBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ProductBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ProductBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public Product build() {
        return new Product(id, name, description, stock, createdAt, updatedAt, category, status, businessKey);
    }
}
