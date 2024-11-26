package dev.jakubdacewicz.product_service.category;

import org.springframework.data.domain.Page;

public interface CategoryRepository {

    Category findById(String id);

    Page<Category> findAll(int page, int size);

    Page<Category> findByNameContainingIgnoreCase(int page, int size, String name);

    Category save(Category category);

    boolean update(String id, String name, String description);

    boolean update(String id, boolean enabled);

    boolean removeProductFromCategory(String categoryId, String productId);

    boolean addProductToCategory(String categoryId, String productId);

    void delete(String id);
}
