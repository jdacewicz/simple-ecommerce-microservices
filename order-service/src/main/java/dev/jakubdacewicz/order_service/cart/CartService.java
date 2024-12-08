package dev.jakubdacewicz.order_service.cart;

import dev.jakubdacewicz.order_service.cart.dto.Cart;

public interface CartService {

    Cart getCart(String id);
}
