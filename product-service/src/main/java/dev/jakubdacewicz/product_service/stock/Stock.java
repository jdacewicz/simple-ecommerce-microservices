package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.shared.types.StockStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "stocks")
public class Stock {

    @Id
    private String id;

    private int quantity;

    private BigDecimal price;

    private StockStatus stockStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Indexed(unique = true)
    private UUID businessKey;

    public Stock(String id,
                 int quantity,
                 BigDecimal price,
                 StockStatus stockStatus,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt,
                 UUID businessKey) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.stockStatus = stockStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.businessKey = businessKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(businessKey, stock.businessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(businessKey);
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UUID getBusinessKey() {
        return businessKey;
    }

    void setId(String id) {
        this.id = id;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    void setPrice(BigDecimal price) {
        this.price = price;
    }

    void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    void setBusinessKey(UUID businessKey) {
        this.businessKey = businessKey;
    }
}
