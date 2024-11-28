package dev.jakubdacewicz.cart_service.cart;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
class CartCalculator {

    public BigDecimal calculateTotalPrice(List<CartItem> cartItems,
                                          Map<String, BigDecimal> productPrices) {
        return cartItems.stream()
                .map(cartItem -> calculateSubtotalPrice(productPrices, cartItem))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateSubtotalPrice(Map<String, BigDecimal> productPrices, CartItem cartItem) {
        BigDecimal price = productPrices.get(cartItem.getProductId());

        return  price.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
