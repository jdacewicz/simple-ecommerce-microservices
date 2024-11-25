package dev.jakubdacewicz.product_service.product.controller;

import dev.jakubdacewicz.product_service.product.ProductService;
import dev.jakubdacewicz.product_service.product.dto.*;
import dev.jakubdacewicz.product_service.shared.types.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestControllerV1 {

    private final ProductService productService;

    ProductRestControllerV1(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public SummaryProductDto getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/{id}/details")
    public DetailedProductDto getProductDetails(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public Page<SummaryProductDto> getProducts(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(required = false) String name) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public ProductCreationResult createProduct(@RequestBody ProductCreationRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}")
    public ProductUpdateResult updateProduct(@PathVariable String id,
                                             @RequestBody ProductUpdateRequest request) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}/status")
    public ProductStatusUpdateResult updateProductStatus(@PathVariable String id,
                                                         @RequestParam ProductStatus status) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public ProductDeletionResult deleteProduct(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }
}
