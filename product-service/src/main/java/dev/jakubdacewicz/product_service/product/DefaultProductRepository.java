package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.shared.exception.DocumentNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

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
}

@Repository
interface MongoProductRepository extends MongoRepository<Product, String> {

}
