package dev.jakubdacewicz.cart_service.cart;

import java.util.Objects;

public class CartItem {

    private String productId;

    private int quantity;

    CartItem() {
    }

    CartItem(String productId,
             int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public void subtractQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(productId, cartItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    void setProductId(String productId) {
        this.productId = productId;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
