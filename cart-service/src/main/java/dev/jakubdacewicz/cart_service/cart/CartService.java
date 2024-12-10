package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {

    SummaryCartDto getMyCart(HttpSession session);

    DetailedCartDto getMyDetailedCart(HttpSession session);

    @Transactional
    CartProductInsertionResult addProductsToMyCart(HttpSession session, String productId, int quantity);

    @Transactional
    CartProductRemovalResult removeProductsFromMyCart(HttpSession session, String productId, int quantity);

    @Transactional
    CartDeletionResult deleteMyCart(HttpSession session);
}
