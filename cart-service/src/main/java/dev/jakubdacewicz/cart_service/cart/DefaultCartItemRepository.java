package dev.jakubdacewicz.cart_service.cart;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<CartItem> findByCartIdAndProductId(String cartId, String productId) {
        return mongoCartItemRepository.findByCartIdAndProductId(cartId, productId);
    }

    @Override
    public void deleteById(String id) {
        mongoCartItemRepository.deleteById(id);
    }
}

@Repository
interface MongoCartItemRepository extends MongoRepository<CartItem, String> {

    List<CartItem> findByCartIdAndProductId(String cartId, String productId);
}
