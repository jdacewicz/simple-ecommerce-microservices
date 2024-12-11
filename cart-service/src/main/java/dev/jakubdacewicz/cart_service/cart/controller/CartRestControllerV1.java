package dev.jakubdacewicz.cart_service.cart.controller;

import dev.jakubdacewicz.cart_service.cart.CartService;
import dev.jakubdacewicz.cart_service.cart.dto.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartRestControllerV1 {

    private final CartService cartService;

    CartRestControllerV1(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/my")
    public SummaryCartDto getMyCart(HttpSession session) {
        return cartService.getMyCart(session);
    }

    @GetMapping("/my/details")
    public DetailedCartDto getMyDetailedCart(HttpSession session) {
        return cartService.getMyDetailedCart(session);
    }

    @GetMapping("/{id}")
    public DetailedCartDto getDetailedCart(@PathVariable String id) {
        return cartService.getDetailedCart(id);
    }

    @PutMapping("/my/products/{productId}/add")
    public CartUpdateResult addProductsToMyCart(HttpSession session,
                                                @PathVariable String productId,
                                                @RequestParam int quantity) {
        return cartService.addProductsToMyCart(session, productId, quantity);
    }

    @PutMapping("/my/products/{productId}/remove")
    public CartUpdateResult removeProductsFromMyCart(HttpSession session,
                                                     @PathVariable String productId,
                                                     @RequestParam int quantity) {
        return cartService.removeProductsFromMyCart(session, productId, quantity);
    }

    @DeleteMapping("/my")
    public CartDeletionResult deleteMyCart(HttpSession session) {
        return cartService.deleteMyCart(session);
    }

    @DeleteMapping("/{id}")
    public CartDeletionResult deleteCart(@PathVariable String id) {
        return cartService.deleteCart(id);
    }
}
