package dev.jakubdacewicz.cart_service.cart;

public interface CartRepository {

    Cart findById(String id);

    Cart save(Cart cart);

    boolean addItem(String cartId, String itemId);
}
