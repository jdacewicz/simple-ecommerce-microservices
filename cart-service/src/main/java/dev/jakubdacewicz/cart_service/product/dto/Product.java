package dev.jakubdacewicz.cart_service.product.dto;

public record Product(

        String id,

        String name,

        Stock stock) {
}
