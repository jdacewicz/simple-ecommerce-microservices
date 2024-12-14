package dev.jakubdacewicz.order_service.cart;

import dev.jakubdacewicz.order_service.cart.dto.Cart;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class DefaultCartService implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCartService.class);

    private final CartFetcher cartFetcher;
    private final CartUpdater cartUpdater;

    DefaultCartService(CartFetcher cartFetcher,
                       CartUpdater cartUpdater) {
        this.cartFetcher = cartFetcher;
        this.cartUpdater = cartUpdater;
    }

    @Retry(name = "cartService")
    @Override
    public Cart getCart(String id) {
        logger.debug("Attempt to get '{}' cart", id);

        Cart cart = cartFetcher.fetchCart(id);

        logger.info("Successfully got '{}' cart", id);
        return cart;
    }

    @Retry(name = "cartService")
    @Override
    public void deleteCart(String id) {
        logger.debug("Attempt to delete '{}' cart", id);

        cartUpdater.deleteCart(id);

        logger.info("Successfully deleted '{}' cart", id);
    }
}
