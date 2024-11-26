package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.shared.types.StockStatus;
import dev.jakubdacewicz.product_service.stock.dto.*;
import org.springframework.transaction.annotation.Transactional;

public interface StockService {

    StockDto createStock(StockCreationRequest request);

    @Transactional
    StockUpdateResult updateStock(String id, StockUpdateRequest request);

    @Transactional
    StockUpdateResult updateStockStatus(String id, StockStatus status);

    @Transactional
    StockDeleteResult deleteStock(String id);
}
