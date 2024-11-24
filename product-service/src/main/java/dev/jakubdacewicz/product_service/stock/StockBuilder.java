package dev.jakubdacewicz.product_service.stock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class StockBuilder {

    private String id;
    private String productId;

    private int quantity = 0;

    private BigDecimal price = BigDecimal.ZERO;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID businessKey = UUID.randomUUID();

    public StockBuilder id(String id) {
        this.id = id;
        return this;
    }

    public StockBuilder productId(String productId) {
        this.productId = productId;
        return this;
    }

    public StockBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public StockBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StockBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public StockBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public StockBuilder businessKey(UUID businessKey) {
        this.businessKey = businessKey;
        return this;
    }

    public Stock build() {
        return new Stock(id, productId, quantity, price, createdAt, updatedAt, businessKey);
    }
}
