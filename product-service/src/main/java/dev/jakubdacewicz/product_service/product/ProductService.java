package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.product.dto.DetailedProductDto;
import dev.jakubdacewicz.product_service.product.dto.SummaryProductDto;
import org.springframework.data.domain.Page;

public interface ProductService {

    SummaryProductDto getProduct(String id);

    DetailedProductDto getProductDetails(String id);

    Page<SummaryProductDto> getProducts(int page, int size, String name);
}
