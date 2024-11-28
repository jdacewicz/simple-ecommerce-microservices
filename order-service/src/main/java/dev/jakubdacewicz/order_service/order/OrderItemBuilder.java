package dev.jakubdacewicz.order_service.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderItemBuilder {

    private Long id;

    private Order order;

    private String productId;

    private String name;

    private MonetaryAmount unitMonetaryAmount;
    private MonetaryAmount totalMonetaryAmount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID businessKey = UUID.randomUUID();

    public OrderItemBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public OrderItemBuilder order(Order order) {
        this.order = order;
        return this;
    }

    public OrderItemBuilder productId(String productId) {
        this.productId = productId;
        return this;
    }

    public OrderItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public OrderItemBuilder unitMonetaryAmount(BigDecimal unitPrice) {
        this.unitMonetaryAmount = new MonetaryAmount(unitPrice, 1);
        return this;
    }

    public OrderItemBuilder totalMonetaryAmount(BigDecimal totalPrice, int totalQuantity) {
        this.totalMonetaryAmount = new MonetaryAmount(totalPrice, totalQuantity);
        return this;
    }

    public OrderItemBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public OrderItemBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public OrderItemBuilder businessKey(UUID businessKey) {
        this.businessKey = businessKey;
        return this;
    }

    public OrderItem build() {
        return new OrderItem(id, order, productId, name, unitMonetaryAmount, totalMonetaryAmount, createdAt,
                updatedAt, businessKey);
    }
}
