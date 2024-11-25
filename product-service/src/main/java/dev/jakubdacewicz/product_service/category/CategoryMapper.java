package dev.jakubdacewicz.product_service.category;

import dev.jakubdacewicz.product_service.category.dto.CategoryCreationRequest;
import dev.jakubdacewicz.product_service.category.dto.DetailedCategoryDto;
import dev.jakubdacewicz.product_service.category.dto.SummaryCategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryCreationRequest request) {
        return new CategoryBuilder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public SummaryCategoryDto toSummaryDto(Category category) {
        return new SummaryCategoryDto(category.getId(), category.getName(), category.getDescription());
    }

    public DetailedCategoryDto toDetailedDto(Category category, long productCount) {
        return new DetailedCategoryDto(category.getId(), category.getName(), category.getDescription(),
                category.getCreatedAt(), category.getUpdatedAt(), productCount, category.isEnabled());
    }
}
