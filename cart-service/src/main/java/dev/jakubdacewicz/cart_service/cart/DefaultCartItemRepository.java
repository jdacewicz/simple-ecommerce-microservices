package dev.jakubdacewicz.cart_service.cart;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
class DefaultCartItemRepository implements CartItemRepository {

    private final MongoCartItemRepository mongoCartItemRepository;

    DefaultCartItemRepository(MongoCartItemRepository mongoCartItemRepository) {
        this.mongoCartItemRepository = mongoCartItemRepository;
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return mongoCartItemRepository.save(cartItem);
    }
}

@Repository
interface MongoCartItemRepository extends MongoRepository<CartItem, String> {

}
