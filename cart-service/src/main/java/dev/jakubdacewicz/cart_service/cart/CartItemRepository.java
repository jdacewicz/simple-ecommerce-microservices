package dev.jakubdacewicz.cart_service.cart;

import java.util.List;

public interface CartItemRepository {

    CartItem save(CartItem cartItem);

    List<CartItem> findByCartIdAndProductId(String cartId, String productId);

    void deleteById(String id);

    void deleteAllByCartId(String id);
}
