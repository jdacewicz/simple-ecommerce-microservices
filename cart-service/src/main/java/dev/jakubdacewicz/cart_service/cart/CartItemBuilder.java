package dev.jakubdacewicz.cart_service.cart;

import java.time.LocalDateTime;
import java.util.UUID;

public class CartItemBuilder {

    private String id;

    private String cartId;
    private String productId;

    private int quantity = 0;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID businessKey = UUID.randomUUID();

    public CartItemBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CartItemBuilder cartId(String cartId) {
        this.cartId = cartId;
        return this;
    }

    public CartItemBuilder productId(String productId) {
        this.productId = productId;
        return this;
    }

    public CartItemBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public CartItemBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CartItemBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public CartItemBuilder businessKey(UUID businessKey) {
        this.businessKey = businessKey;
        return this;
    }

    public CartItem build() {
        return new CartItem(id, cartId, productId, quantity, createdAt, updatedAt, businessKey);
    }
}
