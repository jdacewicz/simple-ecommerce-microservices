package dev.jakubdacewicz.product_service.category;

import dev.jakubdacewicz.product_service.category.dto.CategoryCreationRequest;
import dev.jakubdacewicz.product_service.category.dto.SummaryCategoryDto;
import org.springframework.stereotype.Component;

@Component
class CategoryMapper {

    public Category toCategory(CategoryCreationRequest request) {
        return new CategoryBuilder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public SummaryCategoryDto toSummaryDto(Category category) {
        return new SummaryCategoryDto(category.getId(), category.getName(), category.getDescription());
    }
}
