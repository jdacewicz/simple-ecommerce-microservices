package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.category.Category;
import dev.jakubdacewicz.product_service.category.CategoryBuilder;
import dev.jakubdacewicz.product_service.category.CategoryMapper;
import dev.jakubdacewicz.product_service.category.dto.CategoryUpdateResult;
import dev.jakubdacewicz.product_service.product.dto.*;
import dev.jakubdacewicz.product_service.stock.Stock;
import dev.jakubdacewicz.product_service.stock.StockMapper;
import dev.jakubdacewicz.product_service.stock.dto.StockCreationRequest;
import dev.jakubdacewicz.product_service.stock.dto.StockDto;
import org.springframework.stereotype.Component;

import java.util.List;

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
        StockDto stockDto = stockMapper.toDto(product.getStock());

        return new SummaryProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                stockDto);
    }

    DetailedProductDto toDetailedDto(Product product) {
        StockDto stockDto = stockMapper.toDto(product.getStock());

        DetailedProductDto.Builder builder = new DetailedProductDto.Builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .stock(stockDto)
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt());

        if (product.getCategory() != null) {
            builder.category(categoryMapper.toSummaryDto(product.getCategory()));
        }
        return builder.build();
    }

    Product toProduct(ProductCreationRequest request, StockDto stockDto) {
        Stock stock = stockMapper.toStock(stockDto);

        ProductBuilder builder = new ProductBuilder()
                .name(request.name())
                .description(request.description())
                .stock(stock);

        if (request.categoryId() != null) {
            Category category = new CategoryBuilder()
                    .id(request.categoryId())
                    .build();
            builder.category(category);
        }
        return builder.build();
    }

    StockCreationRequest toStockCreationRequest(ProductCreationRequest request) {
        return new StockCreationRequest(request.price(), request.quantity());
    }

    ProductCategoryUpdateResult toProductCategoryUpdateResult(CategoryUpdateResult categoryUpdateResult,
                                                              boolean productUpdated) {
        return new ProductCategoryUpdateResult(categoryUpdateResult.categoryUpdated(), productUpdated);
    }

    List<SummaryProductDto> toSummaryDtoList(List<Product> products) {
        return products.stream()
                .map(this::toSummaryDto)
                .toList();
    }
}
