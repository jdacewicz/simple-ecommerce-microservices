package dev.jakubdacewicz.order_service.cart;

import dev.jakubdacewicz.order_service.shared.exception.CartFetchFailureException;
import dev.jakubdacewicz.order_service.cart.dto.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
class CartFetcher {

    private static final Logger logger = LoggerFactory.getLogger(CartFetcher.class);

    private final RestClient restClient;

    private final CartUriBuilder cartUriBuilder;

    CartFetcher(@Value("${cart-service.url}") String baseUrl,
                CartUriBuilder cartUriBuilder,
                RestClient.Builder restClientBuilder) {
        this.cartUriBuilder = cartUriBuilder;
        this.restClient = restClientBuilder.baseUrl(baseUrl)
                .build();
    }

    Cart fetchCart(String id) {
        String uri = cartUriBuilder.buildSingleCartFetchUri(id);

        return restClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), (request, response) -> {
                    logger.error("Failed to fetch cart: {}, {}", response.getStatusCode(), response.getStatusText());
                    throw new CartFetchFailureException(response.getStatusText());
                })
                .body(Cart.class);
    }

}
