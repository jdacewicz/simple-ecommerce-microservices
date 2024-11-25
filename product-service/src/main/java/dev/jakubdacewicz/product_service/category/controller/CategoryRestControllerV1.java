package dev.jakubdacewicz.product_service.category.controller;

import dev.jakubdacewicz.product_service.category.CategoryService;
import dev.jakubdacewicz.product_service.category.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/products/categories")
public class CategoryRestControllerV1 {

    private final CategoryService categoryService;

    CategoryRestControllerV1(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public SummaryCategoryDto getCategory(@NotBlank @PathVariable String id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("/{id}/details")
    public DetailedCategoryDto getCategoryDetails(@NotBlank @PathVariable String id) {
        return categoryService.getCategoryDetails(id);
    }

    @GetMapping
    public Page<SummaryCategoryDto> getCategories(@PositiveOrZero @RequestParam(defaultValue = "0") int page,
                                                  @Positive @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(required = false) String name) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public SummaryCategoryDto createCategory(@Valid @RequestBody CategoryCreationRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    public CategoryUpdateResult updateCategory(@NotBlank @PathVariable String id,
                                               @Valid @RequestBody CategoryUpdateRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}/enable")
    public CategoryEnableUpdateResult updateCategoryEnable(@NotBlank @PathVariable String id,
                                                           @RequestParam boolean enabled) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public CategoryDeletionResult deleteCategory(@NotBlank @PathVariable String id) {
        throw new UnsupportedOperationException();
    }
}
