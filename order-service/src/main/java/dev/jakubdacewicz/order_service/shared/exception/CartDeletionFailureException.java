package dev.jakubdacewicz.order_service.shared.exception;

public class CartDeletionFailureException extends RuntimeException {

    public CartDeletionFailureException(String message) {
        super(message);
    }
}
