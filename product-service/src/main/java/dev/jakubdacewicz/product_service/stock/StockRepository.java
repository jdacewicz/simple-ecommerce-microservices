package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.shared.types.StockStatus;

import java.math.BigDecimal;

public interface StockRepository {

    Stock save(Stock stock);

    boolean updatePriceAndQuantity(String id, BigDecimal price, int quantity);

    boolean updatePriceAndQuantityAndStatus(String id, BigDecimal price, int quantity, StockStatus stockStatus);

    boolean updateStatus(String id, StockStatus status);

    void deleteById(String id);
}
