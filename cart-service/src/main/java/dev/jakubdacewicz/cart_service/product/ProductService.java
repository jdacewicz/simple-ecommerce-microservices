package dev.jakubdacewicz.cart_service.product;

import dev.jakubdacewicz.cart_service.product.dto.Product;

import java.util.Set;

public interface ProductService {

    Set<Product> fetchProducts(Set<String> productIds);

    void validateProductExists(String productId);
}
