package dev.jakubdacewicz.cart_service.product;

import dev.jakubdacewicz.cart_service.product.dto.Product;
import dev.jakubdacewicz.cart_service.shared.exception.ProductFetchFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.Set;

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
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), this::handleErrorResponse)
                .body(Product.class);
    }

    Set<Product> fetchProducts(Set<String> productIds) {
        String uri = productUriBuilder.buildMultipleProductUri();
        return restClient.get()
                .uri(uriBuilder -> buildUriWithIds(productIds, uriBuilder, uri))
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), this::handleErrorResponse)
                .body(new ParameterizedTypeReference<>() {});
    }

    private void handleErrorResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
        String errorBody = new String(response.getBody()
                .readAllBytes());

        logger.error("Request failed: Status {}, Body {}", response.getStatusCode(), errorBody);
        throw new ProductFetchFailureException(response.getStatusText());
    }
}
