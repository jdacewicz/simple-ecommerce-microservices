package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.shared.types.StockStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class StockBuilder {

    private String id;

    private int quantity = 0;

    private BigDecimal price = BigDecimal.ZERO;

    private StockStatus stockStatus = StockStatus.NOT_AVAILABLE;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID businessKey = UUID.randomUUID();

    public StockBuilder id(String id) {
        this.id = id;
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

    public StockBuilder stockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
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
        return new Stock(id, quantity, price, stockStatus, createdAt, updatedAt, businessKey);
    }
}
