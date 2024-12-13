package dev.jakubdacewicz.cart_service.cart;

import jakarta.servlet.http.HttpSession;

class SessionCartRetrievalStrategy implements CartRetrievalStrategy<HttpSession> {

    @Override
    public Cart getOrCreateCart(HttpSession type) {
        Cart cart = (Cart) type.getAttribute(CART_ATTRIBUTE_IN_SESSION);

        return (cart != null) ? cart : createNewCart(type);
    }

    @Override
    public Cart createNewCart(HttpSession type) {
        Cart cart = new Cart();

        type.setAttribute(CART_ATTRIBUTE_IN_SESSION, cart);

        return cart;
    }
}
