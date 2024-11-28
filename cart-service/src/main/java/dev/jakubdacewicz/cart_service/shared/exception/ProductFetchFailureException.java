package dev.jakubdacewicz.cart_service.shared.exception;

public class ProductFetchFailureException extends RuntimeException {

    public ProductFetchFailureException(String message) {
        super(message);
    }
}
