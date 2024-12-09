package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.shared.types.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private MonetaryAmount totalMonetaryAmount;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NaturalId
    @Column(nullable = false)
    private UUID businessKey;

    Order(Long id,
          Set<OrderItem> orderItems,
          OrderStatus status,
          MonetaryAmount totalMonetaryAmount,
          LocalDateTime createdAt,
          LocalDateTime updatedAt,
          UUID businessKey) {
        this.id = id;
        this.orderItems = orderItems;
        this.status = status;
        this.totalMonetaryAmount = totalMonetaryAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.businessKey = businessKey;
    }

    Order() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(businessKey, order.businessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(businessKey);
    }

    public Long getId() {
        return id;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public MonetaryAmount getTotalMonetaryAmount() {
        return totalMonetaryAmount;
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

    void setId(Long id) {
        this.id = id;
    }

    void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    void setStatus(OrderStatus status) {
        this.status = status;
    }

    void setTotalMonetaryAmount(MonetaryAmount totalMonetaryAmount) {
        this.totalMonetaryAmount = totalMonetaryAmount;
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
