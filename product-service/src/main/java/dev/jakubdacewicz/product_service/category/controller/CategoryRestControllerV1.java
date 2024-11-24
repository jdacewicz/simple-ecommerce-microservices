package dev.jakubdacewicz.product_service.category.controller;

import dev.jakubdacewicz.product_service.category.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestControllerV1 {

    @GetMapping("/{id}")
    public SummaryCategoryDto getCategory(@PathVariable String id) {
        throw new UnsupportedOperationException();
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
    public CategoryCreationResult createCategory(@RequestBody CategoryCreationRequest request) {
        throw new UnsupportedOperationException();
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
