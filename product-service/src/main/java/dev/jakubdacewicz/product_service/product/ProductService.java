package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.product.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    SummaryProductDto getProduct(String id);

    DetailedProductDto getProductDetails(String id);

    Page<SummaryProductDto> getProducts(int page, int size, String name);

    @Transactional
    SummaryProductDto createProduct(ProductCreationRequest request);

    @Transactional
    ProductUpdateResult updateProduct(String id, ProductUpdateRequest request);

    @Transactional
    ProductCategoryUpdateResult addToCategory(String id, String categoryId);

    @Transactional
    ProductCategoryUpdateResult removeFromCategory(String id);

    @Transactional
    ProductDeletionResult deleteProduct(String id);
}
