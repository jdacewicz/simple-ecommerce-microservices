package dev.jakubdacewicz.cart_service.cart;

import dev.jakubdacewicz.cart_service.cart.dto.*;
import dev.jakubdacewicz.cart_service.product.ProductMapper;
import dev.jakubdacewicz.cart_service.product.dto.Product;
import dev.jakubdacewicz.cart_service.product.ProductService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Service
class DefaultCartService implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCartService.class);

    private final String CART_ATTRIBUTE_IN_SESSION = "CART";

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    private final CartCalculator cartCalculator;

    private final ProductService productService;

    DefaultCartService(CartMapper cartMapper,
                       ProductMapper productMapper,
                       CartCalculator cartCalculator,
                       ProductService productService) {
        this.cartMapper = cartMapper;
        this.productMapper = productMapper;
        this.cartCalculator = cartCalculator;
        this.productService = productService;
    }

    @Override
    public SummaryCartDto getMyCart(HttpSession session) {
        logger.debug("Attempt to get summary cart of session '{}'", session.getId());

        Cart cart = getOrCreateMyCart(session);

        Set<String> productIds = cartMapper.toProductIds(cart.getCartItems());
        Set<Product> products = productService.fetchProducts(productIds);

        Map<String, Product> productPrices = productMapper.toProductCatalog(products);
        BigDecimal totalPrice = cartCalculator.calculateTotalPrice(cart.getCartItems(), productPrices);

        logger.info("Successfully got summary cart of session '{}'", session.getId());
        return cartMapper.toSummaryCartDto(cart, totalPrice);
    }

    @Override
    public DetailedCartDto getMyDetailedCart(HttpSession session) {
        logger.debug("Attempt to get detailed cart of session '{}'", session.getId());

        Cart cart = getOrCreateMyCart(session);

        Set<String> productIds = cartMapper.toProductIds(cart.getCartItems());
        Set<Product> products = productService.fetchProducts(productIds);

        Map<String, Product> productCatalog = productMapper.toProductCatalog(products);
        BigDecimal totalPrice = cartCalculator.calculateTotalPrice(cart.getCartItems(), productCatalog);

        logger.info("Successfully got detailed cart of session '{}'", session.getId());
        return cartMapper.toDetailedCartDto(cart, productCatalog, totalPrice);
    }

    @Override
    public CartUpdateResult addProductsToMyCart(HttpSession session, String productId, int quantity) {
        logger.debug("Attempt to add '{}' product of quantity {} to cart of session '{}'",
                productId, quantity, session.getId());

        productService.validateProductExists(productId);

        Cart cart = getOrCreateMyCart(session);

        CartItem cartItem = new CartItem(productId, quantity);
        cart.addCartItem(cartItem);

        session.setAttribute(CART_ATTRIBUTE_IN_SESSION, cart);

        logger.debug("Successfully added products '{}' of quantity {} to cart of session '{}'",
                productId, quantity, session.getId());
        return new CartUpdateResult(true);
    }

    @Override
    public CartUpdateResult removeProductsFromMyCart(HttpSession session, String productId, int quantity) {
        logger.debug("Attempt to remove '{}' product of quantity {} from cart of session '{}'",
                productId, quantity, session.getId());

        Cart cart = getOrCreateMyCart(session);

        CartItem cartItem = new CartItem(productId, quantity);
        cart.removeCartItem(cartItem);

        session.setAttribute(CART_ATTRIBUTE_IN_SESSION, cart);

        return new CartUpdateResult(true);
    }

    @Override
    public CartDeletionResult deleteMyCart(HttpSession session) {
        logger.debug("Attempt to delete cart of session '{}'", session.getId());

        session.removeAttribute(CART_ATTRIBUTE_IN_SESSION);

        logger.info("Successfully deleted cart of session '{}'", session.getId());
        return new CartDeletionResult(true);
    }

    private Cart getOrCreateMyCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE_IN_SESSION);

        return (cart != null) ? cart : createNewCart(session);
    }

    private Cart createNewCart(HttpSession session) {
        logger.debug("Attempt to create new cart of session '{}'", session.getId());

        Cart cart = new Cart();
        session.setAttribute(CART_ATTRIBUTE_IN_SESSION, cart);

        logger.info("Successfully created new cart of session '{}'", session.getId());
        return cart;
    }
}
