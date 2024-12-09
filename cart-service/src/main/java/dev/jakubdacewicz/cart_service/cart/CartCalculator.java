package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.product.dto.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
class CartCalculator {

    public static final BigDecimal DEFAULT_PRODUCT_PRICE = BigDecimal.ZERO;

    public BigDecimal calculateTotalPrice(List<CartItem> cartItems,
                                          Map<String, Product> productCatalog) {
        return cartItems.stream()
                .map(cartItem -> calculateSubtotalPrice(productCatalog, cartItem))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateSubtotalPrice(Map<String, Product> productCatalog, CartItem cartItem) {
        Product product = productCatalog.get(cartItem.getProductId());
        if (product == null) {
            return DEFAULT_PRODUCT_PRICE;
        }
        return product.stock()
                .price()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
