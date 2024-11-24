package dev.jakubdacewicz.order_service.order;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private MonetaryAmount unitMonetaryAmount;

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

    public OrderItem(Long id,
                     Order order,
                     String productId,
                     String name,
                     MonetaryAmount unitMonetaryAmount,
                     MonetaryAmount totalMonetaryAmount,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt,
                     UUID businessKey) {
        this.id = id;
        this.order = order;
        this.productId = productId;
        this.name = name;
        this.unitMonetaryAmount = unitMonetaryAmount;
        this.totalMonetaryAmount = totalMonetaryAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.businessKey = businessKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(businessKey, orderItem.businessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(businessKey);
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public MonetaryAmount getUnitMonetaryAmount() {
        return unitMonetaryAmount;
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

    void setOrder(Order order) {
        this.order = order;
    }

    void setProductId(String productId) {
        this.productId = productId;
    }

    void setName(String name) {
        this.name = name;
    }

    void setUnitMonetaryAmount(MonetaryAmount unitMonetaryAmount) {
        this.unitMonetaryAmount = unitMonetaryAmount;
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
