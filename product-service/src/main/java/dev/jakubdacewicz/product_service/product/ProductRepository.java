package dev.jakubdacewicz.product_service.product;

public interface ProductRepository {

    Product findById(String id);
}
