package dev.jakubdacewicz.cart_service.cart;

import java.time.LocalDateTime;
import java.util.*;

public class CartBuilder {

    private String id;

    private List<CartItem> cartItems = new LinkedList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID businessKey = UUID.randomUUID();

    public CartBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CartBuilder cartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public CartBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CartBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public CartBuilder businessKey(UUID businessKey) {
        this.businessKey = businessKey;
        return this;
    }

    public Cart build() {
        return new Cart(id, cartItems, createdAt, updatedAt, businessKey);
    }
}
