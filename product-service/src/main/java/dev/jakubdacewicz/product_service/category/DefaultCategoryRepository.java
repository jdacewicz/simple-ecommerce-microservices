package dev.jakubdacewicz.product_service.category;

import dev.jakubdacewicz.product_service.shared.exception.DocumentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
class DefaultCategoryRepository implements CategoryRepository {

    private final MongoCategoryRepository mongoCategoryRepository;

    DefaultCategoryRepository(MongoCategoryRepository mongoCategoryRepository) {
        this.mongoCategoryRepository = mongoCategoryRepository;
    }

    @Override
    public Category findById(String id) {
        return mongoCategoryRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Could not find category with id: " + id));
    }

    @Override
    public Page<Category> findAll(int page, int size) {
        return mongoCategoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Category> findByNameContainingIgnoreCase(int page, int size, String name) {
        return mongoCategoryRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size));
    }

    @Override
    public Category save(Category category) {
        return mongoCategoryRepository.save(category);
    }

    @Override
    public boolean update(String id, String name, String description) {
        return mongoCategoryRepository.updateNameAndDescription(id, name, description) > 0;
    }

    @Override
    public boolean update(String id, boolean enabled) {
        return mongoCategoryRepository.updateEnabled(id, enabled) > 0;
    }

    @Override
    public boolean removeProductFromCategory(String categoryId, String productId) {
        return mongoCategoryRepository.removeProductFromCategory(categoryId, productId) > 0;
    }

    @Override
    public boolean addProductToCategory(String categoryId, String productId) {
        return mongoCategoryRepository.addProductToCategory(categoryId, productId) > 0;
    }

    @Override
    public void delete(String id) {
        mongoCategoryRepository.deleteById(id);
    }
}

@Repository
interface MongoCategoryRepository extends MongoRepository<Category, String> {

    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("{ '_id': ?0 }")
    @Update("{ $set: { 'name': ?1, 'description': ?2 } }")
    int updateNameAndDescription(String id, String name, String description);

    @Query("{ '_id': ?0 }")
    @Update("{ $set: { 'enabled': ?1 } }")
    int updateEnabled(String id, boolean enabled);

    @Query("{ '_id': ?0 }")
    @Update("{ $addToSet: { 'products': ?1 } }")
    int addProductToCategory(String categoryId, String productId);

    @Query("{ '_id': ?0 }")
    @Update("{ $pull: { 'products': ?1 } }")
    int removeProductFromCategory(String categoryId, String productId);
}
