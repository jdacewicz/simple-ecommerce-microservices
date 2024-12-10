package dev.jakubdacewicz.cart_service.cart;

import java.util.HashSet;
import java.util.Set;

public class Cart {

    private Set<CartItem> cartItems = new HashSet<>();

    Cart() {
    }

    Cart(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.stream()
                .filter(item -> item.getProductId()
                        .equals(cartItem.getProductId()))
                .findFirst()
                .ifPresentOrElse(item -> item.setQuantity(item.getQuantity() + cartItem.getQuantity()),
                        () -> cartItems.add(cartItem));

    }

    public void removeCartItem(CartItem newCartItem) {
        cartItems.stream()
                .filter(oldCartItem -> oldCartItem.getProductId()
                        .equals(newCartItem.getProductId()))
                .findFirst()
                .ifPresent(oldCartItem -> updateOrRemoveCartItem(newCartItem,
                        oldCartItem.getQuantity() - newCartItem.getQuantity()));
    }

    public int getTotalQuantity() {
        return cartItems.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    private void updateOrRemoveCartItem(CartItem oldCartItem, int newQuantity) {
        if (newQuantity > 0) {
            oldCartItem.setQuantity(newQuantity);
        } else {
            cartItems.remove(oldCartItem);
        }
    }
}
