package dev.jakubdacewicz.cart_service.product;

import org.springframework.stereotype.Component;

@Component
class ProductUriBuilder {

    String buildSingleProductUri(String productId) {
        return "/products/" + productId + "/price";
    }

    String buildMultipleProductUri() {
        return "/products/price";
    }
}
