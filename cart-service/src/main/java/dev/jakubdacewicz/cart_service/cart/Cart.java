package dev.jakubdacewicz.cart_service.cart;

import java.util.LinkedList;
import java.util.List;

public class Cart {

    private List<CartItem> cartItems = new LinkedList<>();

    Cart() {
    }

    Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getTotalQuantity() {
        return cartItems.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
