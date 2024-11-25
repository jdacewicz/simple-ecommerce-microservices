package dev.jakubdacewicz.product_service.category;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
class DefaultCategoryRepository implements CategoryRepository {

    private final MongoCategoryRepository mongoCategoryRepository;

    DefaultCategoryRepository(MongoCategoryRepository mongoCategoryRepository) {
        this.mongoCategoryRepository = mongoCategoryRepository;
    }

    @Override
    public Category findById(String id) {
        return null;
    }

    @Override
    public Page<Category> findAll(int page, int size, String name) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return mongoCategoryRepository.save(category);
    }

    @Override
    public boolean update(String id, String name, String description) {
        return false;
    }

    @Override
    public boolean update(String id, boolean enabled) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}

@Repository
interface MongoCategoryRepository extends MongoRepository<Category, String> {

}
