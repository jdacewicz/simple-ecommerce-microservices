package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.*;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {

    SummaryCartDto getCart(String id);

    DetailedCartDto getDetailedCart(String id);

    @Transactional
    SummaryCartDto createCart();

    @Transactional
    CartProductInsertionResult addProductsToCart(String cartId, String productId, int quantity);

    @Transactional
    CartProductRemovalResult removeProductsFromCart(String cartId, String productId, int quantity);

    @Transactional
    CartDeletionResult deleteCart(String id);
}
