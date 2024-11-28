package dev.jakubdacewicz.product_service.product.controller;

import dev.jakubdacewicz.product_service.product.ProductService;
import dev.jakubdacewicz.product_service.product.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/products")
public class ProductRestControllerV1 {

    private final ProductService productService;

    ProductRestControllerV1(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public SummaryProductDto getProduct(@NotBlank @PathVariable String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/{id}/details")
    public DetailedProductDto getProductDetails(@NotBlank @PathVariable String id) {
        return productService.getProductDetails(id);
    }

    @GetMapping
    public Page<SummaryProductDto> getProducts(@PositiveOrZero @RequestParam(defaultValue = "0") int page,
                                               @Positive @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(required = false) String name) {
        return productService.getProducts(page, size, name);
    }

    @GetMapping("/list")
    public List<SummaryProductDto> getProducts(@NotNull @RequestParam List<String> ids) {
        return productService.getProductsList(ids);
    }

    @PostMapping
    public SummaryProductDto createProduct(@Valid @RequestBody ProductCreationRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    public ProductUpdateResult updateProduct(@NotBlank @PathVariable String id,
                                             @Valid @RequestBody ProductUpdateRequest request) {
        return productService.updateProduct(id, request);
    }

    @PutMapping("/{productId}/categories/{categoryId}/add")
    public ProductCategoryUpdateResult addToCategory(@NotBlank @PathVariable String productId,
                                                     @NotBlank @PathVariable String categoryId) {
        return productService.addToCategory(productId, categoryId);
    }

    @PutMapping("/{id}/categories/remove")
    public ProductCategoryUpdateResult removeFromCategory(@NotBlank @PathVariable String id) {
        return productService.removeFromCategory(id);
    }

    @DeleteMapping("/{id}")
    public ProductDeletionResult deleteProduct(@NotBlank @PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
