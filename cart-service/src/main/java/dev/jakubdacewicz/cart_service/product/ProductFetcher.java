package dev.jakubdacewicz.cart_service.product;

import dev.jakubdacewicz.cart_service.product.dto.Product;
import dev.jakubdacewicz.cart_service.shared.exception.ProductFetchFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

import static dev.jakubdacewicz.cart_service.shared.utils.UriUtils.buildUriWithIds;

@Component
class ProductFetcher {

    private static final Logger logger = LoggerFactory.getLogger(ProductFetcher.class);
    private final RestClient restClient;

    private final ProductUriBuilder productUriBuilder;

    public ProductFetcher(@Value("${product-service.url}") String baseUrl,
                          RestClient.Builder restClientBuilder,
                          ProductUriBuilder productUriBuilder) {
        this.productUriBuilder = productUriBuilder;
        this.restClient = restClientBuilder.baseUrl(baseUrl)
                .build();
    }

    Product fetchProduct(String productId) {
        String uri = productUriBuilder.buildSingleProductUri(productId);
        return restClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), (request, response) -> {
                    logger.error("Failed to fetch product: {}, {}", response.getStatusCode(), response.getStatusText());
                    throw new ProductFetchFailureException(response.getStatusText());
                })
                .body(Product.class);
    }

    List<Product> fetchProducts(List<String> productIds) {
        String uri = productUriBuilder.buildMultipleProductUri();
        return restClient.get()
                .uri(uriBuilder -> buildUriWithIds(productIds, uriBuilder, uri))
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), (request, response) -> {
                    logger.error("Failed to fetch products: {}, {}", response.getStatusCode(), response.getStatusText());
                    throw new ProductFetchFailureException(response.getStatusText());
                })
                .body(new ParameterizedTypeReference<>() {});
    }
}
