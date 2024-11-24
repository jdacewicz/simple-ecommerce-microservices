package dev.jakubdacewicz.cart_service.cart.dto;

public record SummaryCartDto(

        String id,

        int totalQuantity,

        int totalPrice) {
}
