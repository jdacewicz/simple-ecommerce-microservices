package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.stock.dto.StockDeleteResult;
import dev.jakubdacewicz.product_service.stock.dto.StockUpdateRequest;
import dev.jakubdacewicz.product_service.stock.dto.StockUpdateResult;
import org.springframework.transaction.annotation.Transactional;

public interface StockService {

    @Transactional
    StockUpdateResult updateStock(String id, StockUpdateRequest request);

    @Transactional
    StockDeleteResult deleteStock(String id);
}
