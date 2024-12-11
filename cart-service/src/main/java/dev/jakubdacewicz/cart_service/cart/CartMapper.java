package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.CartItemDto;
import dev.jakubdacewicz.cart_service.cart.dto.DetailedCartDto;
import dev.jakubdacewicz.cart_service.cart.dto.SummaryCartDto;
import dev.jakubdacewicz.cart_service.product.dto.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class CartMapper {

    SummaryCartDto toSummaryCartDto(Cart cart, BigDecimal totalPrice) {
        return new SummaryCartDto(cart.getTotalQuantity(), totalPrice);
    }

    DetailedCartDto toDetailedCartDto(Cart cart,
                                      Map<String, Product> productCatalog,
                                      BigDecimal totalPrice) {
        Set<CartItemDto> cartItems = toCartItemDto(cart.getCartItems(), productCatalog);

        return new DetailedCartDto(
                cartItems,
                cart.getTotalQuantity(),
                totalPrice);
    }

    Set<CartItemDto> toCartItemDto(Set<CartItem> cartItems,
                                    Map<String, Product> productCatalog) {
        return cartItems.stream()
                .map(cartItem -> toCartItemDto(cartItem, productCatalog.get(cartItem.getProductId())))
                .collect(Collectors.toSet());
    }

    CartItemDto toCartItemDto(CartItem cartItem, Product product) {
        return new CartItemDto(
                cartItem.getProductId(),
                product.name(),
                cartItem.getQuantity(),
                product.stock().price());
    }

    Set<String> toProductIds(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartItem::getProductId)
                .collect(Collectors.toSet());
    }
}
