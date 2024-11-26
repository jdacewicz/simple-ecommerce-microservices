package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.category.Category;
import dev.jakubdacewicz.product_service.stock.Stock;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String description;

    @DBRef(lazy = true)
    private Stock stock;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @DBRef(lazy = true)
    private Category category;

    @Indexed(unique = true)
    private UUID businessKey;

    public Product(String id,
                   String name,
                   String description,
                   Stock stock,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt,
                   Category category,
                   UUID businessKey) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.businessKey = businessKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(businessKey, product.businessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(businessKey);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Category getCategory() {
        return category;
    }

    public UUID getBusinessKey() {
        return businessKey;
    }

    void setId(String id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setStock(Stock stock) {
        this.stock = stock;
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    void setCategory(Category category) {
        this.category = category;
    }

    void setBusinessKey(UUID businessKey) {
        this.businessKey = businessKey;
    }
}
