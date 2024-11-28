package dev.jakubdacewicz.cart_service.cart;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
class CartCalculator {

    public static final BigDecimal DEFAULT_PRODUCT_PRICE = BigDecimal.ZERO;

    public BigDecimal calculateTotalPrice(List<CartItem> cartItems,
                                          Map<String, BigDecimal> productPrices) {
        return cartItems.stream()
                .map(cartItem -> calculateSubtotalPrice(productPrices, cartItem))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateSubtotalPrice(Map<String, BigDecimal> productPrices, CartItem cartItem) {
        BigDecimal price = productPrices.getOrDefault(cartItem.getProductId(), DEFAULT_PRODUCT_PRICE);

        return  price.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
