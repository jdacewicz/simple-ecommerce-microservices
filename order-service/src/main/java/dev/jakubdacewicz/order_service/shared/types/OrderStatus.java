package dev.jakubdacewicz.order_service.shared.types;

public enum OrderStatus {

    CREATED,
    PAYMENT_PENDING,
    PAYMENT_CONFIRMED,
    PAYMENT_FAILED,
    CANCELLED,
    REFUNDED,
    DECLINED,
    IN_PROGRESS,
    MANUAL_VERIFICATION_REQUIRED,
    SHIPPED
}
