package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.cart.CartService;
import dev.jakubdacewicz.order_service.order.dto.DetailedOrderDto;
import dev.jakubdacewicz.order_service.order.dto.OrderCreationRequest;
import dev.jakubdacewicz.order_service.shared.model.Cart;
import dev.jakubdacewicz.order_service.shared.types.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class DefaultOrderService implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderService.class);

    private final OrderRepository orderRepository;

    private final CartService cartService;

    private final OrderMapper orderMapper;

    DefaultOrderService(OrderRepository orderRepository, CartService cartService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.orderMapper = orderMapper;
    }

    @Override
    public DetailedOrderDto createOrder(OrderCreationRequest request) {
        logger.debug("Attempt to create order");

        Cart cart = cartService.getCart(request.cartId());

        Order order = orderMapper.toOrder(cart);
        order.setStatus(OrderStatus.CREATED);

        Order newOrder = orderRepository.save(order);

        logger.info("Created order {}", newOrder.getId());
        return orderMapper.toDetailedOrderDto(newOrder);
    }
}
