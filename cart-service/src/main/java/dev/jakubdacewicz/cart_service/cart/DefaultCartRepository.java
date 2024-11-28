package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.shared.exception.DocumentNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
class DefaultCartRepository implements CartRepository {

    private final MongoCartRepository mongoCartRepository;

    DefaultCartRepository(MongoCartRepository mongoCartRepository) {
        this.mongoCartRepository = mongoCartRepository;
    }

    @Override
    public Cart findById(String id) {
        return mongoCartRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Could not find cart with id: " + id));
    }

    @Override
    public Cart save(Cart cart) {
        return mongoCartRepository.save(cart);
    }

    @Override
    public boolean addItem(String cartId, String itemId) {
        return mongoCartRepository.addItemToCart(cartId, itemId) > 0;
    }

    @Override
    public boolean removeItem(String cartId, String itemId) {
        return mongoCartRepository.removeItemFromCart(cartId, itemId) > 0;
    }
}

@Repository
interface MongoCartRepository extends MongoRepository<Cart, String> {

    @Query("{ '_id': ?0 }")
    @Update("{ $addToSet: { 'cartItems': ?1 } }")
    int addItemToCart(String cartId, String itemId);

    @Query("{ '_id': ?0 }")
    @Update("{ $pull: { 'cartItems': ?1 } }")
    int removeItemFromCart(String cartId, String itemId);
}
