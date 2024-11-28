package dev.jakubdacewicz.cart_service.product;

import dev.jakubdacewicz.cart_service.product.dto.Product;

import java.util.List;

public interface ProductService {

    List<Product> fetchProducts(List<String> productIds);

    void validateProductExists(String productId);
}
