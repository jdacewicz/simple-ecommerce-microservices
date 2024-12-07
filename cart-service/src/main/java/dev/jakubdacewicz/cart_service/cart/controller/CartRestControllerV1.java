package dev.jakubdacewicz.cart_service.cart.controller;

import dev.jakubdacewicz.cart_service.cart.CartService;
import dev.jakubdacewicz.cart_service.cart.dto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartRestControllerV1 {

    private final CartService cartService;

    CartRestControllerV1(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public SummaryCartDto getCart(@PathVariable String id) {
        return cartService.getCart(id);
    }

    @GetMapping("/{id}/details")
    public DetailedCartDto getDetailedCart(@PathVariable String id) {
        return cartService.getDetailedCart(id);
    }

    @PostMapping
    public SummaryCartDto createCart() {
        return cartService.createCart();
    }

    @PutMapping("/{cartId}/products/{productId}/add")
    public CartProductInsertionResult addProductsToCart(@PathVariable String cartId,
                                                        @PathVariable String productId,
                                                        @RequestParam int quantity) {
        return cartService.addProductsToCart(cartId, productId, quantity);
    }

    @PutMapping("/{cartId}/products/{productId}/remove")
    public CartProductRemovalResult removeProductsFromCart(@PathVariable String cartId,
                                                           @PathVariable String productId,
                                                           @RequestParam int quantity) {
        return cartService.removeProductsFromCart(cartId, productId, quantity);
    }

    @DeleteMapping("/{id}")
    public CartDeletionResult deleteCart(@PathVariable String id) {
        return cartService.deleteCart(id);
    }
}
