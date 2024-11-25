package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.stock.dto.StockUpdateRequest;
import dev.jakubdacewicz.product_service.stock.dto.StockUpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class DefaultStockService implements StockService{

    private static final Logger logger = LoggerFactory.getLogger(DefaultStockService.class);

    private final StockRepository stockRepository;

    DefaultStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockUpdateResult updateStock(String id, StockUpdateRequest request) {
        logger.debug("Attempt to update '{}' stock", id);

        boolean updatedStock = stockRepository.updatePriceAndQuantity(Integer.parseInt(id), request.price(),
                request.quantity());

        logger.info("Successfully updated '{}' stock", id);
        return new StockUpdateResult(updatedStock);
    }
}
