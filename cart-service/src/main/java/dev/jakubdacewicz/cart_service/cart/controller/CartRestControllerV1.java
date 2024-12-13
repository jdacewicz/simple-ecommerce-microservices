package dev.jakubdacewicz.cart_service.cart.controller;

import dev.jakubdacewicz.cart_service.cart.CartService;
import dev.jakubdacewicz.cart_service.cart.dto.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/carts")
public class CartRestControllerV1 {

    private final CartService cartService;

    CartRestControllerV1(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/my")
    public SummaryCartDto getMyCart(@NotNull HttpSession session) {
        return cartService.getMyCart(session);
    }

    @GetMapping("/my/details")
    public DetailedCartDto getMyDetailedCart(@NotNull HttpSession session) {
        return cartService.getMyDetailedCart(session);
    }

    @GetMapping("/{sessionId}/details")
    public DetailedCartDto getDetailedCart(@PathVariable String sessionId) {
        return cartService.getDetailedCart(sessionId);
    }

    @PutMapping("/my/products/{productId}/add")
    public CartUpdateResult addProductsToMyCart(@NotNull HttpSession session,
                                                @NotBlank @PathVariable String productId,
                                                @Positive @RequestParam int quantity) {
        return cartService.addProductsToMyCart(session, productId, quantity);
    }

    @PutMapping("/my/products/{productId}/remove")
    public CartUpdateResult removeProductsFromMyCart(@NotNull HttpSession session,
                                                     @NotBlank @PathVariable String productId,
                                                     @Positive @RequestParam int quantity) {
        return cartService.removeProductsFromMyCart(session, productId, quantity);
    }

    @DeleteMapping("/my")
    public CartDeletionResult deleteMyCart(@NotNull HttpSession session) {
        return cartService.deleteMyCart(session);
    }

    @DeleteMapping("/{sessionId}")
    public CartDeletionResult deleteCart(@NotBlank @PathVariable String sessionId) {
        return cartService.deleteCart(sessionId);
    }
}
