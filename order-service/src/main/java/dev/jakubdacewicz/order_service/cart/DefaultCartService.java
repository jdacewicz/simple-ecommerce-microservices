package dev.jakubdacewicz.order_service.cart;

import dev.jakubdacewicz.order_service.cart.dto.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class DefaultCartService implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCartService.class);

    private final CartFetcher cartFetcher;

    DefaultCartService(CartFetcher cartFetcher) {
        this.cartFetcher = cartFetcher;
    }

    @Override
    public Cart getCart(String id) {
        logger.debug("Attempt to get '{}' cart", id);

        Cart cart = cartFetcher.fetchCart(id);

        logger.info("Successfully got '{}' cart", id);
        return cart;
    }
}
