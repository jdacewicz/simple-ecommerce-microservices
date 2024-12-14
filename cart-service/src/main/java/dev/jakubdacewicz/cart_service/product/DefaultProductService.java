package dev.jakubdacewicz.cart_service.product;

import dev.jakubdacewicz.cart_service.product.dto.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
class DefaultProductService implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultProductService.class);

    private final ProductFetcher productFetcher;

    DefaultProductService(ProductFetcher productFetcher) {
        this.productFetcher = productFetcher;
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "fetchProductsFallback")
    @Retry(name = "productService")
    @Override
    public Set<Product> fetchProducts(Set<String> productIds) {
        logger.debug("Attempt to get '{}' products", productIds.size());

        Set<Product> products = productFetcher.fetchProducts(productIds);

        logger.info("Successfully got '{}' products", products.size());
        return products;
    }

    @Retry(name = "productService")
    @Override
    public void validateProductExists(String productId) {
        logger.debug("Attempt to get '{}' product", productId);

        productFetcher.fetchProduct(productId);

        logger.info("Successfully got '{}' product", productId);
    }

    private Set<Product> fetchProductsFallback(Set<String> productIds, Exception e) {
        logger.warn("Fallback triggered for products {}: {}", productIds, e.getMessage());
        return Set.of();
    }
}
