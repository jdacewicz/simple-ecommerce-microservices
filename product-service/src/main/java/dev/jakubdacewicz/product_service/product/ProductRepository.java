package dev.jakubdacewicz.product_service.product;

import org.springframework.data.domain.Page;

public interface ProductRepository {

    Product findById(String id);

    Page<Product> findAll(int page, int size);

    Page<Product> findByNameContainingIgnoreCase(int page, int size, String name);

    Product save(Product product);

    void deleteById(String id);
}
