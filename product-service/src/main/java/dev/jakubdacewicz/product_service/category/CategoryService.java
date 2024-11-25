package dev.jakubdacewicz.product_service.category;

import dev.jakubdacewicz.product_service.category.dto.*;
import org.springframework.data.domain.Page;

public interface CategoryService {

    SummaryCategoryDto getCategory(String id);

    DetailedCategoryDto getCategoryDetails(String id);

    Page<SummaryCategoryDto> getCategories(int page, int size, String name);

    SummaryCategoryDto createCategory(CategoryCreationRequest request);

    DetailedCategoryDto updateCategory(String id, CategoryUpdateRequest request);

    DetailedCategoryDto updateCategoryEnable(String id, boolean enabled);

    CategoryDeletionResult deleteCategory(String id);
}
