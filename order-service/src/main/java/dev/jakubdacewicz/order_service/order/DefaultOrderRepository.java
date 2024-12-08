package dev.jakubdacewicz.order_service.order;

import dev.jakubdacewicz.order_service.shared.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
class DefaultOrderRepository implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    DefaultOrderRepository(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        return jpaOrderRepository.save(order);
    }

    @Override
    public Order findById(long id) {
        return jpaOrderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Could not find order with id: " + id));
    }
}

@Repository
interface JpaOrderRepository extends JpaRepository<Order, Long> {

}
