package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.product.dto.DetailedProductDto;
import dev.jakubdacewicz.product_service.product.dto.SummaryProductDto;

public interface ProductService {

    SummaryProductDto getProduct(String id);

    DetailedProductDto getProductDetails(String id);
}
