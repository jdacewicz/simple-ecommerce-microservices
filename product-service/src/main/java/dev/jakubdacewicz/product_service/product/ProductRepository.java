package dev.jakubdacewicz.product_service.product;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductRepository {

    Product findById(String id);

    Page<Product> findAll(int page, int size);

    List<Product> findAll(List<String> ids);

    Page<Product> findByNameContainingIgnoreCase(int page, int size, String name);

    Product save(Product product);

    boolean updateNameAndDescription(String id, String name, String description);

    boolean updateCategory(String id, String categoryId);

    boolean resetCategory(String id);

    void deleteById(String id);
}
