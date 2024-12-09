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
        Order order = new OrderBuilder()
                .totalMonetaryAmount(cart.totalPrice(), cart.totalQuantity())
                .build();

        Set<OrderItem> orderItems = toOrderItems(order, cart.cartItems());
        order.setOrderItems(orderItems);

        return order;
    }

    DetailedOrderDto toDetailedOrderDto(Order newOrder) {
        List<OrderItemDto> orderItems = toOrderItemDto(newOrder.getOrderItems());
        return new DetailedOrderDto(newOrder.getId(),
                orderItems,
                newOrder.getStatus(),
                newOrder.getTotalMonetaryAmount(),
                newOrder.getCreatedAt());
    }

    private Set<OrderItem> toOrderItems(Order order, List<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> toOrderItem(order, cartItem))
                .collect(Collectors.toSet());
    }

    private OrderItem toOrderItem(Order order, CartItem cartItem) {
        return new OrderItemBuilder()
                .order(order)
                .productId(cartItem.productId())
                .name(cartItem.name())
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
