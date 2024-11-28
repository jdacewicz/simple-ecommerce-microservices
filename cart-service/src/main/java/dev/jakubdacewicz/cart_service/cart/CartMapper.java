package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.SummaryCartDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartMapper {

    SummaryCartDto toSummaryCartDto(Cart cart, BigDecimal totalPrice) {
        return new SummaryCartDto(cart.getId(), cart.getTotalQuantity(), totalPrice);
    }
}
