package dev.jakubdacewicz.product_service.stock.controller;

import dev.jakubdacewicz.product_service.shared.types.StockStatus;
import dev.jakubdacewicz.product_service.stock.StockService;
import dev.jakubdacewicz.product_service.stock.dto.StockUpdateRequest;
import dev.jakubdacewicz.product_service.stock.dto.StockUpdateResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/stocks")
public class StockRestControllerV1 {

    private final StockService stockService;

    StockRestControllerV1(StockService stockService) {
        this.stockService = stockService;
    }

    @PutMapping("/{id}")
    public StockUpdateResult updateStock(@NotBlank @PathVariable String id,
                                         @Valid @RequestBody StockUpdateRequest request) {
        return stockService.updateStock(id, request);
    }

    @PutMapping("/{id}/status")
    public StockUpdateResult updateStockStatus(@NotBlank @PathVariable String id,
                                               @NotNull @RequestParam StockStatus status) {
        return stockService.updateStockStatus(id, status);
    }
}
