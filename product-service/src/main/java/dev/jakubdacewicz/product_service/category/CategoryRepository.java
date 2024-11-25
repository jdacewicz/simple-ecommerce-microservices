package dev.jakubdacewicz.product_service.category;

import org.springframework.data.domain.Page;

public interface CategoryRepository {

    Category findById(String id);

    Page<Category> findAll(int page, int size);

    Page<Category> findByNameContainingIgnoreCase(int page, int size, String name);

    long countProducts(String name);

    Category save(Category category);

    boolean update(String id, String name, String description);

    boolean update(String id, boolean enabled);

    void delete(String id);
}
