package dev.jakubdacewicz.order_service.order;

interface OrderRepository {

    Order save(Order order);

    Order findById(long id);
}
