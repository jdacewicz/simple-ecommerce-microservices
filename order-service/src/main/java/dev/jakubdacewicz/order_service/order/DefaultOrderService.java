package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.cart.CartService;
import dev.jakubdacewicz.order_service.order.dto.DetailedOrderDto;
import dev.jakubdacewicz.order_service.order.dto.OrderCreationRequest;
import dev.jakubdacewicz.order_service.cart.dto.Cart;
import dev.jakubdacewicz.order_service.order.dto.OrderStatusUpdateResult;
import dev.jakubdacewicz.order_service.order.dto.SummaryOrderDto;
import dev.jakubdacewicz.order_service.shared.types.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
    public SummaryOrderDto getOrder(long id) {
        logger.debug("Attempt to get summary of order {}", id);

        Order order = orderRepository.findById(id);

        logger.info("Successfully got summary of order {}", id);
        return orderMapper.toSummaryOrderDto(order);
    }

    @Override
    public DetailedOrderDto getDetailedOrder(long id) {
        logger.debug("Attempt to get details of order {}", id);

        Order order = orderRepository.findById(id);

        logger.info("Successfully got details of order {}", id);
        return orderMapper.toDetailedOrderDto(order);
    }

    @Override
    public Page<SummaryOrderDto> getOrders(int page, int size) {
        logger.debug("Attempt to get all orders: page {}, size {}", page, size);

        Page<Order> orders = orderRepository.findAll(page, size);

        logger.info("Successfully got all orders: page {}, size {}", page, size);
        return orders.map(orderMapper::toSummaryOrderDto);
    }

    @Override
    public DetailedOrderDto createOrder(OrderCreationRequest request) {
        logger.debug("Attempt to create order");

        Cart cart = cartService.getCart(request.cartId());

        Order order = orderMapper.toOrder(cart);
        order.setStatus(OrderStatus.CREATED);

        Order newOrder = orderRepository.save(order);
        cartService.deleteCart(request.cartId());

        logger.info("Created order {}", newOrder.getId());
        return orderMapper.toDetailedOrderDto(newOrder);
    }

    @Override
    public OrderStatusUpdateResult updateOrderStatus(long id, OrderStatus status) {
        logger.debug("Attempt to update status of order {} to {}", id, status);

        boolean statusUpdated = orderRepository.updateStatus(id, status);

        logger.info("Successfully updated status of order {} to {}", id, status);
        return new OrderStatusUpdateResult(statusUpdated, status.name());
    }
}
