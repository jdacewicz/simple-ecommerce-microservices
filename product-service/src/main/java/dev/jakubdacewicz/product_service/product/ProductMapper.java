package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.category.CategoryMapper;
import dev.jakubdacewicz.product_service.category.dto.SummaryCategoryDto;
import dev.jakubdacewicz.product_service.product.dto.DetailedProductDto;
import dev.jakubdacewicz.product_service.product.dto.SummaryProductDto;
import dev.jakubdacewicz.product_service.stock.StockMapper;
import dev.jakubdacewicz.product_service.stock.dto.StockDto;
import org.springframework.stereotype.Component;

@Component
class ProductMapper {

    private final CategoryMapper categoryMapper;
    private final StockMapper stockMapper;

    ProductMapper(CategoryMapper categoryMapper,
                  StockMapper stockMapper) {
        this.categoryMapper = categoryMapper;
        this.stockMapper = stockMapper;
    }

    SummaryProductDto toSummaryDto(Product product) {
        SummaryCategoryDto summaryCategoryDto = categoryMapper.toSummaryDto(product.getCategory());
        return new SummaryProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                summaryCategoryDto,
                product.getStock().getPrice(),
                product.getStatus().name());
    }

    DetailedProductDto toDetailedDto(Product product) {
        SummaryCategoryDto summaryCategoryDto = categoryMapper.toSummaryDto(product.getCategory());
        StockDto stockDto = stockMapper.toDto(product.getStock());
        return new DetailedProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                summaryCategoryDto,
                stockDto,
                product.getStatus().name(),
                product.getCreatedAt(),
                product.getUpdatedAt());
    }
}
