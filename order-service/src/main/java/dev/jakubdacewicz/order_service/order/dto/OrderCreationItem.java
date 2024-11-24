package dev.jakubdacewicz.order_service.order.dto;

public record OrderCreationItem(

        String productId,

        int quantity) {
}
