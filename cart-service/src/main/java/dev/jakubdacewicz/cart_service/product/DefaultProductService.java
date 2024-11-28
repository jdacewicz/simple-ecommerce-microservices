package dev.jakubdacewicz.cart_service.product;

import dev.jakubdacewicz.cart_service.product.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class DefaultProductService implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultProductService.class);

    private final ProductFetcher productFetcher;

    DefaultProductService(ProductFetcher productFetcher) {
        this.productFetcher = productFetcher;
    }

    @Override
    public List<Product> fetchProducts(List<String> productIds) {
        logger.debug("Attempt to get '{}' products", productIds.size());

        List<Product> products = productFetcher.fetchProducts(productIds);

        logger.info("Successfully got '{}' products", products.size());
        return products;
    }

    @Override
    public void validateProductExists(String productId) {
        logger.debug("Attempt to get '{}' product", productId);

        productFetcher.fetchProduct(productId);

        logger.info("Successfully got '{}' product", productId);
    }
}
