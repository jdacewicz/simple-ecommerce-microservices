package dev.jakubdacewicz.cart_service.cart;

public interface CartRetrievalStrategy<T> {

    String CART_ATTRIBUTE_IN_SESSION = "CART";

    Cart getOrCreateCart(T type);

    Cart createNewCart(T type);
}
