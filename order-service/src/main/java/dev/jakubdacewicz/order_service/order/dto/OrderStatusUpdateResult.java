package dev.jakubdacewicz.order_service.order.dto;

public record OrderStatusUpdateResult(

        boolean statusUpdated,

        String newStatus) {
}
