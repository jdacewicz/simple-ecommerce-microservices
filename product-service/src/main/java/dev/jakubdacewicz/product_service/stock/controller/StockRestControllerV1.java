package dev.jakubdacewicz.product_service.stock.controller;

import dev.jakubdacewicz.product_service.stock.StockService;
import dev.jakubdacewicz.product_service.stock.dto.StockUpdateRequest;
import dev.jakubdacewicz.product_service.stock.dto.StockUpdateResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockRestControllerV1 {

    private final StockService stockService;

    StockRestControllerV1(StockService stockService) {
        this.stockService = stockService;
    }

    @PutMapping("/{id}")
    public StockUpdateResult updateStock(@PathVariable String id,
                                         @RequestBody StockUpdateRequest request) {
        return stockService.updateStock(id, request);
    }
}
