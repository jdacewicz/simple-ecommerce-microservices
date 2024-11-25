package dev.jakubdacewicz.product_service.category.controller;

import dev.jakubdacewicz.product_service.category.CategoryService;
import dev.jakubdacewicz.product_service.category.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products/categories")
public class CategoryRestControllerV1 {

    private final CategoryService categoryService;

    CategoryRestControllerV1(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public SummaryCategoryDto getCategory(@PathVariable String id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("/{id}/details")
    public DetailedCategoryDto getCategoryDetails(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public Page<SummaryCategoryDto> getCategories(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(required = false) String name) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public SummaryCategoryDto createCategory(@RequestBody CategoryCreationRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    public CategoryUpdateResult updateCategory(@PathVariable String id,
                                               @RequestBody CategoryUpdateRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}/enable")
    public CategoryEnableUpdateResult updateCategoryEnable(@PathVariable String id,
                                                           @RequestParam boolean enabled) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public CategoryDeletionResult deleteCategory(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }
}
