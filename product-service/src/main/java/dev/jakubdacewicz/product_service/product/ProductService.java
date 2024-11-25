package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.product.dto.DetailedProductDto;
import dev.jakubdacewicz.product_service.product.dto.ProductCreationRequest;
import dev.jakubdacewicz.product_service.product.dto.ProductDeletionResult;
import dev.jakubdacewicz.product_service.product.dto.SummaryProductDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    SummaryProductDto getProduct(String id);

    DetailedProductDto getProductDetails(String id);

    Page<SummaryProductDto> getProducts(int page, int size, String name);

    DetailedProductDto createProduct(ProductCreationRequest request);

    @Transactional
    ProductDeletionResult deleteProduct(String id);
}
