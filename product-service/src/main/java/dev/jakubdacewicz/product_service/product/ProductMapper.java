package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.category.CategoryMapper;
import dev.jakubdacewicz.product_service.category.dto.SummaryCategoryDto;
import dev.jakubdacewicz.product_service.product.dto.SummaryProductDto;
import org.springframework.stereotype.Component;

@Component
class ProductMapper {

    private final CategoryMapper categoryMapper;

    ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    SummaryProductDto toSummaryDto(Product product) {
        SummaryCategoryDto summaryCategoryDto = categoryMapper.toSummaryDto(product.getCategory());
        return new SummaryProductDto(product.getId(), product.getName(), product.getDescription(), summaryCategoryDto,
                product.getStock().getPrice(), product.getStatus().name());
    }
}
