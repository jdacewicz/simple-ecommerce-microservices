package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {

    SummaryCartDto getMyCart(HttpSession session);

    DetailedCartDto getMyDetailedCart(HttpSession session);

    @Transactional
    CartUpdateResult addProductsToMyCart(HttpSession session, String productId, int quantity);

    @Transactional
    CartUpdateResult removeProductsFromMyCart(HttpSession session, String productId, int quantity);

    @Transactional
    CartDeletionResult deleteMyCart(HttpSession session);
}
