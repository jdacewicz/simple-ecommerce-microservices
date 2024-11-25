package dev.jakubdacewicz.product_service.shared.model;

public record ApiError(

        int code,

        String message) {
}
