package dev.jakubdacewicz.order_service.shared.exception;

public class CartFetchFailureException extends RuntimeException {

    public CartFetchFailureException(String message) {
        super(message);
    }
}
