package dev.jakubdacewicz.cart_service.cart;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
class DefaultCartRepository implements CartRepository {

    private final MongoCartRepository mongoCartRepository;

    DefaultCartRepository(MongoCartRepository mongoCartRepository) {
        this.mongoCartRepository = mongoCartRepository;
    }

    @Override
    public Cart save(Cart cart) {
        return mongoCartRepository.save(cart);
    }
}

@Repository
interface MongoCartRepository extends MongoRepository<Cart, String> {

}
