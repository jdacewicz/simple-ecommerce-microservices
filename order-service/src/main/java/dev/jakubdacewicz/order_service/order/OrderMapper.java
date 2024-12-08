package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.order.dto.DetailedOrderDto;
import dev.jakubdacewicz.order_service.order.dto.OrderItemDto;
import dev.jakubdacewicz.order_service.cart.dto.Cart;
import dev.jakubdacewicz.order_service.cart.dto.CartItem;
import dev.jakubdacewicz.order_service.order.dto.SummaryOrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class OrderMapper {

    SummaryOrderDto toSummaryOrderDto(Order order) {
        return new SummaryOrderDto(order.getId(),
                order.getStatus(),
                order.getTotalMonetaryAmount(),
                order.getCreatedAt());
    }

    Order toOrder(Cart cart) {
        Set<OrderItem> orderItems = toOrderItems(cart.cartItems());
        return new OrderBuilder()
                .orderItems(orderItems)
                .totalMonetaryAmount(cart.totalPrice(), cart.totalQuantity())
                .build();
    }

    DetailedOrderDto toDetailedOrderDto(Order newOrder) {
        List<OrderItemDto> orderItems = toOrderItemDto(newOrder.getOrderItems());
        return new DetailedOrderDto(newOrder.getId(),
                orderItems,
                newOrder.getStatus(),
                newOrder.getTotalMonetaryAmount(),
                newOrder.getCreatedAt());
    }

    private Set<OrderItem> toOrderItems(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::toOrderItem)
                .collect(Collectors.toSet());
    }

    private OrderItem toOrderItem(CartItem cartItem) {
        return new OrderItemBuilder()
                .productId(cartItem.productId())
                .unitMonetaryAmount(cartItem.price())
                .totalMonetaryAmount(cartItem.price(), cartItem.quantity())
                .build();
    }

    private List<OrderItemDto> toOrderItemDto(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());
    }

    private OrderItemDto toOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getName(),
                orderItem.getUnitMonetaryAmount(),
                orderItem.getTotalMonetaryAmount()
        );
    }
}
