package dev.jakubdacewicz.order_service.order.dto;

import java.util.Set;

public record OrderCreationRequest(

        Set<OrderCreationItem> items) {
}
