package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.shared.types.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class OrderBuilder {

    private Long id;

    private Set<OrderItem> orderItems = new HashSet<>();

    private OrderStatus status = OrderStatus.IN_PROGRESS;

    private MonetaryAmount totalMonetaryAmount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID businessKey = UUID.randomUUID();

    public OrderBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder orderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public OrderBuilder status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public OrderBuilder totalMonetaryAmount(BigDecimal totalPrice, int totalQuantity) {
        this.totalMonetaryAmount = new MonetaryAmount(totalPrice, totalQuantity);
        return this;
    }

    public OrderBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public OrderBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public OrderBuilder businessKey(UUID businessKey) {
        this.businessKey = businessKey;
        return this;
    }

    public Order build() {
        return new Order(id, orderItems, status, totalMonetaryAmount, createdAt, updatedAt, businessKey);
    }
}
