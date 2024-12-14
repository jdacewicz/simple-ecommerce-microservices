package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.product.dto.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Component
class CartCalculator {

    public static final BigDecimal DEFAULT_PRODUCT_PRICE = BigDecimal.ZERO;

    public BigDecimal calculateTotalPrice(Set<CartItem> cartItems,
                                          Map<String, Product> productCatalog) {
        return cartItems.stream()
                .filter(cartItem -> cartItem != null && productCatalog.get(cartItem.getProductId()) != null)
                .map(cartItem -> calculateSubtotalPrice(productCatalog, cartItem))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateSubtotalPrice(Map<String, Product> productCatalog,
                                              CartItem cartItem) {
        Product product = productCatalog.get(cartItem.getProductId());
        if (product == null) {
            return DEFAULT_PRODUCT_PRICE;
        }
        return product.stock()
                .price()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
