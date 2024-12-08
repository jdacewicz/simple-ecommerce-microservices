package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.*;
import dev.jakubdacewicz.cart_service.product.dto.Product;
import dev.jakubdacewicz.cart_service.product.ProductService;
import dev.jakubdacewicz.cart_service.shared.exception.DocumentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
class DefaultCartService implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCartService.class);

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final CartMapper cartMapper;

    private final CartCalculator cartCalculator;

    private final ProductService productService;

    DefaultCartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       CartMapper cartMapper,
                       CartCalculator cartCalculator,
                       ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartMapper = cartMapper;
        this.cartCalculator = cartCalculator;
        this.productService = productService;
    }

    @Override
    public SummaryCartDto getCart(String id) {
        logger.debug("Attempt to get '{}' summary cart", id);

        Cart cart = cartRepository.findById(id);

        List<String> productIds = cartMapper.toProductIds(cart.getCartItems());
        List<Product> products = productService.fetchProducts(productIds);

        Map<String, BigDecimal> productPrices = cartMapper.toPriceMap(products);
        BigDecimal totalPrice = cartCalculator.calculateTotalPrice(cart.getCartItems(), productPrices);

        logger.info("Successfully got '{}' summary cart", id);
        return cartMapper.toSummaryCartDto(cart, totalPrice);
    }

    @Override
    public DetailedCartDto getDetailedCart(String id) {
        logger.debug("Attempt to get '{}' detailed cart", id);

        Cart cart = cartRepository.findById(id);

        List<String> productIds = cartMapper.toProductIds(cart.getCartItems());
        List<Product> products = productService.fetchProducts(productIds);

        Map<String, BigDecimal> productPrices = cartMapper.toPriceMap(products);
        BigDecimal totalPrice = cartCalculator.calculateTotalPrice(cart.getCartItems(), productPrices);

        logger.info("Successfully got '{}' detailed cart", id);
        return cartMapper.toDetailedCartDto(cart, productPrices, totalPrice);
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

        productService.validateProductExists(productId);

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

    @Override
    public CartDeletionResult deleteCart(String id) {
        logger.debug("Attempt to delete '{}' cart", id);

        cartItemRepository.deleteAllByCartId(id);
        cartRepository.deleteById(id);

        logger.info("Successfully deleted '{}' cart", id);
        return new CartDeletionResult(true);
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
