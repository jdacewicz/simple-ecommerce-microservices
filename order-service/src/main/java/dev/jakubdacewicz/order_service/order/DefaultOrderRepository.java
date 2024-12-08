package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.shared.exception.RecordNotFoundException;
import dev.jakubdacewicz.order_service.shared.types.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
class DefaultOrderRepository implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    DefaultOrderRepository(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order findById(long id) {
        return jpaOrderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Could not find order with id: " + id));
    }

    @Override
    public Page<Order> findAll(int page, int size) {
        return jpaOrderRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Order save(Order order) {
        return jpaOrderRepository.save(order);
    }

    @Override
    public boolean updateStatus(long id, OrderStatus status) {
        return jpaOrderRepository.updateStatus(id, status) > 0;
    }
}

@Repository
interface JpaOrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("UPDATE Order o " +
            "SET o.status = :status " +
            "WHERE o.id = :id")
    int updateStatus(long id, OrderStatus status);
}
