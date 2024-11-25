package dev.jakubdacewicz.product_service.stock;

import java.math.BigDecimal;

public interface StockRepository {

    boolean updatePriceAndQuantity(int id, BigDecimal price, int quantity);
}
