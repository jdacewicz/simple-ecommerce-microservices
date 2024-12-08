package dev.jakubdacewicz.cart_service.cart;

public interface CartRepository {

    Cart findById(String id);

    Cart save(Cart cart);

    boolean addItem(String cartId, String itemId);

    boolean removeItem(String cartId, String itemId);

    void deleteById(String id);
}
