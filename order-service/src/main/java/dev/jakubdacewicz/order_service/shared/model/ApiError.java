package dev.jakubdacewicz.order_service.shared.model;

public record ApiError(

        int code,

        String message) {
}
