package dev.jakubdacewicz.cart_service.product;

import org.springframework.stereotype.Component;

@Component
class ProductUriBuilder {

    private static final String API_PREFIX = "/api/v1";

    String buildSingleProductUri(String productId) {
        return API_PREFIX + "/products/" + productId;
    }

    String buildMultipleProductUri() {
        return API_PREFIX + "/products/list";
    }
}
