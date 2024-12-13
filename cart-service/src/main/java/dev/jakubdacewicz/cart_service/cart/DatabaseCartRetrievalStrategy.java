package dev.jakubdacewicz.cart_service.cart;

import org.springframework.session.Session;

class DatabaseCartRetrievalStrategy implements CartRetrievalStrategy<Session> {

    @Override
    public Cart getOrCreateCart(Session type) {
        Cart cart = type.getAttribute(CART_ATTRIBUTE_IN_SESSION);

        return (cart != null) ? cart : createNewCart(type);
    }

    @Override
    public Cart createNewCart(Session type) {
        Cart cart = new Cart();

        type.setAttribute(CART_ATTRIBUTE_IN_SESSION, cart);

        return cart;
    }
}
