package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.CartProductInsertionResult;
import dev.jakubdacewicz.cart_service.cart.dto.SummaryCartDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class DefaultCartService implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCartService.class);

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final CartMapper cartMapper;

    DefaultCartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public SummaryCartDto getCart(String id) {
        logger.debug("Attempt to get '{}' cart", id);

        Cart cart = cartRepository.findById(id);
        //Fetch total price from product service
        BigDecimal totalPrice = BigDecimal.ZERO;

        logger.info("Successfully got '{}' cart", id);
        return cartMapper.toSummaryCartDto(cart, totalPrice);
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

    @Override
    public CartProductInsertionResult addProductsToCart(String cartId, String productId, int quantity) {
        logger.debug("Attempt to add '{}' product of quantity {} to '{}' cart", productId, quantity, cartId);

        //Fetch check product exists

        CartItem cartItem = new CartItemBuilder()
                .productId(productId)
                .quantity(quantity)
                .build();
        CartItem newCartItem = cartItemRepository.save(cartItem);

        boolean itemAdded = cartRepository.addItem(cartId, newCartItem.getId());

        logger.info("Successfully added '{}' product of quantity {} to '{}' cart", productId, quantity, cartId);
        return new CartProductInsertionResult(itemAdded);
    }

    @Override
    public SummaryCartDto removeProductsFromCart(String cartId, String productId, int quantity) {
        return null;
    }
}
