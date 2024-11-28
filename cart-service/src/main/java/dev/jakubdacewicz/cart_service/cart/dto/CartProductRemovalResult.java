package dev.jakubdacewicz.cart_service.cart.dto;

public record CartProductRemovalResult(

        boolean productQuantityChanged,

        boolean productRemoved) {
}
