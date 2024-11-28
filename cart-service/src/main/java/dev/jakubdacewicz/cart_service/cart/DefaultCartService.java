package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.CartProductInsertionResult;
import dev.jakubdacewicz.cart_service.cart.dto.CartProductRemovalResult;
import dev.jakubdacewicz.cart_service.cart.dto.SummaryCartDto;
import dev.jakubdacewicz.cart_service.shared.exception.DocumentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

        Cart cart = cartRepository.findById(cartId);

        CartItem cartItem = new CartItemBuilder()
                .cartId(cart.getId())
                .productId(productId)
                .quantity(quantity)
                .build();
        CartItem newCartItem = cartItemRepository.save(cartItem);

        boolean itemAdded = cartRepository.addItem(cartId, newCartItem.getId());

        logger.info("Successfully added '{}' product of quantity {} to '{}' cart", productId, quantity, cartId);
        return new CartProductInsertionResult(itemAdded);
    }

    @Override
    public CartProductRemovalResult removeProductsFromCart(String cartId, String productId, int quantity) {
        logger.debug("Attempt to remove '{}' product of quantity {} from '{}' cart", productId, quantity, cartId);

        CartItem firstCartItem = getFirstCartItem(cartId, productId);
        firstCartItem.subtractQuantity(quantity);

        if (firstCartItem.getQuantity() > 0) {
            return updateQuantityOfCartItem(cartId, productId, quantity, firstCartItem);
        } else {
            return deleteCartItem(cartId, productId, firstCartItem);
        }
    }

    private CartItem getFirstCartItem(String cartId, String productId) {
        List<CartItem> cartItems = cartItemRepository.findByCartIdAndProductId(cartId, productId);
        if (cartItems.isEmpty()) {
            logger.info("Could not find product with id: {} in cart with id: {}", productId, cartId);
            throw new DocumentNotFoundException("Could not find product with id: " + productId
                    + " in cart with id: " + cartId);
        }
        return cartItems.getFirst();
    }

    private CartProductRemovalResult updateQuantityOfCartItem(String cartId, String productId,
                                                              int quantity, CartItem firstCartItem) {
        cartItemRepository.save(firstCartItem);

        logger.info("Successfully removed '{}' product of quantity {} from '{}' cart", productId, quantity, cartId);
        return new CartProductRemovalResult(true, false);
    }

    private CartProductRemovalResult deleteCartItem(String cartId, String productId, CartItem firstCartItem) {
        boolean productRemoved = cartRepository.removeItem(cartId, firstCartItem.getId());
        cartItemRepository.deleteById(firstCartItem.getId());

        logger.info("Successfully removed all '{}' product from '{}' cart", productId, cartId);
        return new CartProductRemovalResult(false, productRemoved);
    }
}
