package dev.jakubdacewicz.product_service.shared.exception;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(String message) {
        super(message);
    }
}
