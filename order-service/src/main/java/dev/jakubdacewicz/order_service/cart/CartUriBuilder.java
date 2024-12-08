package dev.jakubdacewicz.order_service.cart;

import org.springframework.stereotype.Component;

@Component
class CartUriBuilder {

    private static final String API_PREFIX = "/api/v1";

    String buildSingleCartUri(String cartId) {
        return API_PREFIX + "/carts/" + cartId + "/details";
    }
}
