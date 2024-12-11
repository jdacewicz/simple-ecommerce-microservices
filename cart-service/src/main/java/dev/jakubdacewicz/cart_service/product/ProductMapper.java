package dev.jakubdacewicz.cart_service.product;

import dev.jakubdacewicz.cart_service.product.dto.Product;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Map<String, Product> toProductCatalog(Set<Product> products) {
        return products.stream()
                .collect(Collectors.toMap(Product::id, product -> product));
    }
}
