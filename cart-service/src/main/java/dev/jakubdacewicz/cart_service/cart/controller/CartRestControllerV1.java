package dev.jakubdacewicz.cart_service.cart.controller;

import dev.jakubdacewicz.cart_service.dto.CartDeletionResult;
import dev.jakubdacewicz.cart_service.dto.DetailedCartDto;
import dev.jakubdacewicz.cart_service.dto.SummaryCartDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartRestControllerV1 {

    @GetMapping("/{id}")
    public SummaryCartDto getCart(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{id}/details")
    public DetailedCartDto getDetailedCart(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public SummaryCartDto createCart() {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{cartId}/items/{itemId}/add")
    public SummaryCartDto addItemsToCart(@PathVariable String cartId,
                                         @PathVariable String itemId,
                                         @RequestParam int quantity) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{cartId}/items/{itemId}/remove")
    public SummaryCartDto removeItemsFromCart(@PathVariable String cartId,
                                              @PathVariable String itemId,
                                              @RequestParam int quantity) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public CartDeletionResult deleteCart(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }
}
