package dev.jakubdacewicz.product_service.stock;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
class DefaultStockRepository implements StockRepository {

    private final MongoStockRepository mongoStockRepository;

    DefaultStockRepository(MongoStockRepository mongoStockRepository) {
        this.mongoStockRepository = mongoStockRepository;
    }

    @Override
    public boolean updatePriceAndQuantity(int id, BigDecimal price, int quantity) {
        return false;
    }
}

@Repository
interface MongoStockRepository extends MongoRepository<Stock, String> {

}
