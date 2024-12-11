package dev.jakubdacewicz.cart_service.shared.utils;

import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Set;

public class UriUtils {

    public static URI buildUriWithIds(Set<String> productIds, UriBuilder uriBuilder, String uri) {
        return uriBuilder
                .path(uri)
                .queryParam("ids", String.join(",", productIds))
                .build();
    }
}
