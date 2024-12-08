package dev.jakubdacewicz.order_service.cart;

import dev.jakubdacewicz.order_service.shared.exception.CartDeletionFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
class CartUpdater {

    private static final Logger logger = LoggerFactory.getLogger(CartUpdater.class);

    private final RestClient restClient;

    private final CartUriBuilder cartUriBuilder;

    CartUpdater(@Value("${cart-service.url}") String baseUrl,
                CartUriBuilder cartUriBuilder,
                RestClient.Builder restClientBuilder) {
        this.cartUriBuilder = cartUriBuilder;
        this.restClient = restClientBuilder.baseUrl(baseUrl)
                .build();
    }

    void deleteCart(String id) {
        String uri = cartUriBuilder.buildCartDeletionUri(id);

        restClient.delete()
                .uri(uri)
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), (request, response) -> {
                    logger.error("Failed to delete cart: {}, {}", response.getStatusCode(), response.getStatusText());
                    throw new CartDeletionFailureException(response.getStatusText());
                });
    }
}
