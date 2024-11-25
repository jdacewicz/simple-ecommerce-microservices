package dev.jakubdacewicz.product_service.category;

import dev.jakubdacewicz.product_service.product.Product;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CategoryBuilder {

    private String id;

    private String name;
    private String description = "";

    private Set<Product> products = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean enabled = false;

    private UUID businessKey = UUID.randomUUID();

    public CategoryBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public CategoryBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CategoryBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public CategoryBuilder enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public CategoryBuilder businessKey(UUID businessKey) {
        this.businessKey = businessKey;
        return this;
    }

    public Category build() {
        return new Category(id, name, description, products, createdAt, updatedAt, enabled, businessKey);
    }
}
