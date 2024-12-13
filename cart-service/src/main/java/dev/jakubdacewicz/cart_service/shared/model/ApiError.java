package dev.jakubdacewicz.cart_service.shared.model;

public record ApiError(

        int code,
        String message) {
}
