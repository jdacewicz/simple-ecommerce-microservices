package dev.jakubdacewicz.product_service.category;

import dev.jakubdacewicz.product_service.category.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryService {

    SummaryCategoryDto getCategory(String id);

    DetailedCategoryDto getCategoryDetails(String id);

    Page<SummaryCategoryDto> getCategories(int page, int size, String name);

    SummaryCategoryDto createCategory(CategoryCreationRequest request);

    @Transactional
    CategoryUpdateResult updateCategory(String id, CategoryUpdateRequest request);

    @Transactional
    CategoryUpdateResult updateCategoryEnable(String id, boolean enabled);

    @Transactional
    CategoryUpdateResult removeProductFromCategory(String categoryId, String productId);

    @Transactional
    CategoryUpdateResult addProductToCategory(String categoryId, String id);

    @Transactional
    CategoryDeletionResult deleteCategory(String id);
}
