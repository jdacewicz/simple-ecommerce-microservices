package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.shared.types.StockStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
class DefaultStockRepository implements StockRepository {

    private final MongoStockRepository mongoStockRepository;

    DefaultStockRepository(MongoStockRepository mongoStockRepository) {
        this.mongoStockRepository = mongoStockRepository;
    }

    @Override
    public Stock save(Stock stock) {
        return mongoStockRepository.save(stock);
    }

    @Override
    public boolean updatePriceAndQuantity(String id, BigDecimal price, int quantity) {
        return mongoStockRepository.updatePriceAndQuantity(id, price, quantity) > 0;
    }

    @Override
    public boolean updatePriceAndQuantityAndStatus(String id, BigDecimal price, int quantity, StockStatus stockStatus) {
        return mongoStockRepository.updatePriceAndQuantityAndStatus(id, price, quantity, stockStatus) > 0;
    }

    @Override
    public boolean updateStatus(String id, StockStatus status) {
        return mongoStockRepository.updateStatus(id, status) > 0;
    }

    @Override
    public void deleteById(String id) {
        mongoStockRepository.deleteById(id);
    }
}

@Repository
interface MongoStockRepository extends MongoRepository<Stock, String> {

    @Query("{ '_id': ?0 }")
    @Update("{ $set: { 'price': ?1, 'quantity': ?2 } }")
    int updatePriceAndQuantity(String id, BigDecimal price, int quantity);

    @Query("{ '_id': ?0 }")
    @Update("{ $set: { 'price': ?1, 'quantity': ?2, 'stockStatus': ?3 } }")
    int updatePriceAndQuantityAndStatus(String id, BigDecimal price, int quantity, StockStatus stockStatus);

    @Query("{ '_id': ?0 }")
    @Update("{ $set: { 'stockStatus': ?1 } }")
    int updateStatus(String id, StockStatus stockStatus);
}
