package dev.jakubdacewicz.product_service.stock;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class StockValidator {

    void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater or equal 0.");
        }
    }

    void validatePrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null.");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }
}
