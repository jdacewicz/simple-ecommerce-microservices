package dev.jakubdacewicz.order_service.order;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record MonetaryAmount(

        BigDecimal price,

        int quantity) {

    public MonetaryAmount {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
}
