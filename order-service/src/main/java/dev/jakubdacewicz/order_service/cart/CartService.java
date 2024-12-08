package dev.jakubdacewicz.order_service.cart;

import dev.jakubdacewicz.order_service.shared.model.Cart;

public interface CartService {

    Cart getCart(String id);
}
