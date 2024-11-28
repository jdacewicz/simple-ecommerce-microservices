package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.CartItemDto;
import dev.jakubdacewicz.cart_service.cart.dto.DetailedCartDto;
import dev.jakubdacewicz.cart_service.cart.dto.SummaryCartDto;
import dev.jakubdacewicz.cart_service.product.dto.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
class CartMapper {

    SummaryCartDto toSummaryCartDto(Cart cart, BigDecimal totalPrice) {
        return new SummaryCartDto(cart.getId(), cart.getTotalQuantity(), totalPrice);
    }

    DetailedCartDto toDetailedCartDto(Cart cart, Map<String, BigDecimal> productPrices, BigDecimal totalPrice) {
        List<CartItemDto> cartItems = toCartItemDto(cart.getCartItems(), productPrices);

        return new DetailedCartDto(cart.getId(),
                cartItems,
                cart.getTotalQuantity(),
                totalPrice,
                cart.getUpdatedAt());
    }

    List<CartItemDto> toCartItemDto(List<CartItem> cartItems, Map<String, BigDecimal> productPrices) {
        return cartItems.stream()
                .map(cartItem -> toCartItemDto(cartItem, productPrices.get(cartItem.getProductId())))
                .toList();
    }

    CartItemDto toCartItemDto(CartItem cartItem, BigDecimal price) {
        return new CartItemDto(
                cartItem.getId(),
                cartItem.getProductId(),
                cartItem.getQuantity(),
                price,
                cartItem.getUpdatedAt()
        );
    }

    List<String> toProductIds(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartItem::getProductId)
                .toList();
    }

    Map<String, BigDecimal> toPriceMap(List<Product> products) {
        return products.stream()
                .collect(Collectors.toMap(Product::id, product -> product.stock().price()));
    }
}
