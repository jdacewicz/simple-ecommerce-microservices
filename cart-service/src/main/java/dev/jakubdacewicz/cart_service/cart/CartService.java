package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.CartProductInsertionResult;
import dev.jakubdacewicz.cart_service.cart.dto.SummaryCartDto;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {

    SummaryCartDto getCart(String id);

    @Transactional
    SummaryCartDto createCart();

    @Transactional
    CartProductInsertionResult addProductsToCart(String cartId, String productId, int quantity);

    @Transactional
    SummaryCartDto removeProductsFromCart(String cartId, String productId, int quantity);
}
