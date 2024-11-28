package dev.jakubdacewicz.cart_service.cart;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Document(collection = "carts")
public class Cart {

    @Id
    private String id;

    @DBRef(lazy = true)
    private Set<CartItem> cartItems;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Indexed(unique = true)
    private UUID businessKey;

    public Cart(String id,
                Set<CartItem> cartItems,
                LocalDateTime createdAt,
                LocalDateTime updatedAt,
                UUID businessKey) {
        this.id = id;
        this.cartItems = cartItems;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.businessKey = businessKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(businessKey, cart.businessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(businessKey);
    }

    public String getId() {
        return id;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
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

    void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
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
