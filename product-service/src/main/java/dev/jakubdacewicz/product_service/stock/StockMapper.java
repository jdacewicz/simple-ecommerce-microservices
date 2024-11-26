package dev.jakubdacewicz.product_service.stock;

import dev.jakubdacewicz.product_service.stock.dto.StockCreationRequest;
import dev.jakubdacewicz.product_service.stock.dto.StockDto;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockDto toDto(Stock stock) {
        return new StockDto(stock.getId(), stock.getQuantity(), stock.getPrice(), stock.getStockStatus());
    }

    public Stock toStock(StockDto stockDto) {
        return new StockBuilder()
                .id(stockDto.id())
                .quantity(stockDto.quantity())
                .price(stockDto.price())
                .stockStatus(stockDto.status())
                .build();
    }

    public Stock toStock(StockCreationRequest request) {
        return new StockBuilder()
                .quantity(request.quantity())
                .price(request.price())
                .build();
    }
}
