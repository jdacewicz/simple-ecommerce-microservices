package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.category.Category;
import dev.jakubdacewicz.product_service.category.CategoryBuilder;
import dev.jakubdacewicz.product_service.category.CategoryMapper;
import dev.jakubdacewicz.product_service.category.dto.SummaryCategoryDto;
import dev.jakubdacewicz.product_service.product.dto.DetailedProductDto;
import dev.jakubdacewicz.product_service.product.dto.ProductCreationRequest;
import dev.jakubdacewicz.product_service.product.dto.SummaryProductDto;
import dev.jakubdacewicz.product_service.stock.Stock;
import dev.jakubdacewicz.product_service.stock.StockBuilder;
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
        StockDto stockDto = stockMapper.toDto(product.getStock());
        return new SummaryProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                summaryCategoryDto,
                stockDto);
    }

    DetailedProductDto toDetailedDto(Product product) {
        SummaryCategoryDto summaryCategoryDto = categoryMapper.toSummaryDto(product.getCategory());
        StockDto stockDto = stockMapper.toDto(product.getStock());

        return new DetailedProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                summaryCategoryDto,
                stockDto,
                product.getCreatedAt(),
                product.getUpdatedAt());
    }

    Product toProduct(ProductCreationRequest request) {
        Stock stock = new StockBuilder()
                .price(request.price())
                .quantity(request.quantity())
                .build();
        Category category = new CategoryBuilder()
                .id(request.categoryId())
                .build();

        return new ProductBuilder()
                .name(request.name())
                .description(request.description())
                .stock(stock)
                .category(category)
                .build();
    }
}
