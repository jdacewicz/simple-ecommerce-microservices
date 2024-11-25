package dev.jakubdacewicz.product_service.product.controller;

import dev.jakubdacewicz.product_service.product.ProductService;
import dev.jakubdacewicz.product_service.product.dto.*;
import dev.jakubdacewicz.product_service.shared.types.ProductStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ProductCreationResult createProduct(@Valid @RequestBody ProductCreationRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}")
    public ProductUpdateResult updateProduct(@NotBlank @PathVariable String id,
                                             @Valid @RequestBody ProductUpdateRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}/status")
    public ProductStatusUpdateResult updateProductStatus(@NotBlank @PathVariable String id,
                                                         @NotNull @RequestParam ProductStatus status) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public ProductDeletionResult deleteProduct(@NotBlank @PathVariable String id) {
        throw new UnsupportedOperationException();
    }
}
