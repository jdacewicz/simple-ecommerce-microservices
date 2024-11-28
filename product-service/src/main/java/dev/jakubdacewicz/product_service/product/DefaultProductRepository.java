package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.shared.exception.DocumentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class DefaultProductRepository implements ProductRepository {

    private final MongoProductRepository mongoProductRepository;

    DefaultProductRepository(MongoProductRepository mongoProductRepository) {
        this.mongoProductRepository = mongoProductRepository;
    }

    @Override
    public Product findById(String id) {
        return mongoProductRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Could not find product with id: " + id));
    }

    @Override
    public Page<Product> findAll(int page, int size) {
        return mongoProductRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Product> findAll(List<String> ids) {
        return mongoProductRepository.findByIdIn(ids);
    }

    @Override
    public Page<Product> findByNameContainingIgnoreCase(int page, int size, String name) {
        return mongoProductRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size));
    }

    @Override
    public Product save(Product product) {
        return mongoProductRepository.save(product);
    }

    @Override
    public boolean updateNameAndDescription(String id, String name, String description) {
        return mongoProductRepository.updateNameAndDescription(id, name, description) > 0;
    }

    @Override
    public boolean updateCategory(String id, String categoryId) {
        return mongoProductRepository.updateCategory(id, categoryId) > 0;
    }

    @Override
    public boolean resetCategory(String id) {
        return mongoProductRepository.resetCategory(id) > 0;
    }

    @Override
    public void deleteById(String id) {
        mongoProductRepository.deleteById(id);
    }
}

@Repository
interface MongoProductRepository extends MongoRepository<Product, String> {

    List<Product> findByIdIn(List<String> ids);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("{ '_id': ?0 }")
    @Update("{ $set: { 'name': ?1, 'description': ?2 } }")
    int updateNameAndDescription(String id, String name, String description);

    @Query("{ '_id': ?0 }")
    @Update("{ $set: { 'category': ?1 } }")
    int updateCategory(String productId, String categoryId);

    @Query("{ '_id': ?0 }")
    @Update("{ $unset: { 'category': '' } }")
    int resetCategory(String productId);
}
