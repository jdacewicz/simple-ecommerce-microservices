package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.CartItemDto;
import dev.jakubdacewicz.cart_service.cart.dto.DetailedCartDto;
import dev.jakubdacewicz.cart_service.cart.dto.SummaryCartDto;
import dev.jakubdacewicz.cart_service.product.dto.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
class CartMapper {

    SummaryCartDto toSummaryCartDto(Cart cart, BigDecimal totalPrice) {
        return new SummaryCartDto(cart.getId(), cart.getTotalQuantity(), totalPrice);
    }

    DetailedCartDto toDetailedCartDto(Cart cart,
                                      Map<String, Product> productCatalog,
                                      BigDecimal totalPrice) {
        List<CartItemDto> cartItems = toCartItemDto(cart.getCartItems(), productCatalog);

        return new DetailedCartDto(cart.getId(),
                cartItems,
                cart.getTotalQuantity(),
                totalPrice,
                cart.getUpdatedAt());
    }

    List<CartItemDto> toCartItemDto(List<CartItem> cartItems,
                                    Map<String, Product> productCatalog) {
        return cartItems.stream()
                .map(cartItem -> toCartItemDto(cartItem, productCatalog.get(cartItem.getProductId())))
                .toList();
    }

    CartItemDto toCartItemDto(CartItem cartItem, Product product) {
        return new CartItemDto(
                cartItem.getId(),
                cartItem.getProductId(),
                product.name(),
                cartItem.getQuantity(),
                product.stock().price(),
                cartItem.getUpdatedAt()
        );
    }

    List<String> toProductIds(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartItem::getProductId)
                .toList();
    }
}
