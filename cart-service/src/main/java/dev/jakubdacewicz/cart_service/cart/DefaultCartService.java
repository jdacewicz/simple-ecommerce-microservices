package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.SummaryCartDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class DefaultCartService implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCartService.class);

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    DefaultCartService(CartRepository cartRepository,
                       CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public SummaryCartDto createCart() {
        logger.debug("Attempt to create cart");

        Cart cart = new CartBuilder()
                .build();
        Cart newCart = cartRepository.save(cart);

        logger.info("Successfully created cart '{}'", newCart.getId());
        return cartMapper.toSummaryCartDto(newCart, BigDecimal.ZERO);
    }
}
