package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.stock.dto.StockDto;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockDto toDto(Stock stock) {
        return new StockDto(stock.getId(), stock.getProductId(), stock.getQuantity(), stock.getPrice());
    }
}
