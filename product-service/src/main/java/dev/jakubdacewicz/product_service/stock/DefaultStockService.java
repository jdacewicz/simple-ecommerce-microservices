package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.shared.types.StockStatus;
import dev.jakubdacewicz.product_service.stock.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class DefaultStockService implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultStockService.class);

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;

    private final StockValidator stockValidator;

    DefaultStockService(StockRepository stockRepository,
                        StockMapper stockMapper,
                        StockValidator stockValidator) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
        this.stockValidator = stockValidator;
    }

    @Override
    public StockDto createStock(StockCreationRequest request) {
        logger.debug("Attempt to create stock");

        stockValidator.validatePrice(request.price());
        stockValidator.validateQuantity(request.quantity());

        Stock stock = stockMapper.toStock(request);
        Stock newStock = stockRepository.save(stock);

        logger.info("Successfully created stock");
        return stockMapper.toDto(newStock);
    }

    @Override
    public StockUpdateResult updateStock(String id, StockUpdateRequest request) {
        logger.debug("Attempt to update '{}' stock", id);

        stockValidator.validatePrice(request.price());
        stockValidator.validateQuantity(request.quantity());

        boolean updatedStock;
        if (request.quantity() < 1) {
            updatedStock = stockRepository.updatePriceAndQuantityAndStatus(id, request.price(), request.quantity(),
                    StockStatus.OUT_OF_STOCK);
        } else {
            updatedStock = stockRepository.updatePriceAndQuantity(id, request.price(), request.quantity());
        }

        logger.info("Successfully updated '{}' stock", id);
        return new StockUpdateResult(updatedStock);
    }

    @Override
    public StockUpdateResult updateStockStatus(String id, StockStatus status) {
        logger.debug("Attempt to update '{}' stock status", id);

        boolean updatedStock = stockRepository.updateStatus(id, status);

        logger.info("Successfully updated '{}' stock status", id);
        return new StockUpdateResult(updatedStock);
    }

    @Override
    public StockDeleteResult deleteStock(String id) {
        logger.debug("Attempt to delete '{}' stock", id);

        stockRepository.deleteById(id);

        logger.info("Successfully deleted '{}' stock", id);
        return new StockDeleteResult(true);
    }
}
