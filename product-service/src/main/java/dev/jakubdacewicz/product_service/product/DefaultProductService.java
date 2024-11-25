package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.product.dto.*;
import dev.jakubdacewicz.product_service.shared.exception.CategoryAssignedException;
import dev.jakubdacewicz.product_service.stock.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
class DefaultProductService implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultProductService.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final StockService stockService;

    DefaultProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          StockService stockService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.stockService = stockService;
    }

    @Override
    public SummaryProductDto getProduct(String id) {
        logger.debug("Attempt to get '{}' simple product", id);

        Product product = productRepository.findById(id);

        logger.info("Successfully got '{}' simple product", id);
        return productMapper.toSummaryDto(product);
    }

    @Override
    public DetailedProductDto getProductDetails(String id) {
        logger.debug("Attempt to get '{}' detailed product", id);

        Product product = productRepository.findById(id);

        logger.info("Successfully got '{}' detailed product", id);
        return productMapper.toDetailedDto(product);
    }

    @Override
    public Page<SummaryProductDto> getProducts(int page, int size, String name) {
        logger.debug("Attempt to get all products");

        Page<Product> products;
        if (name == null || name.isBlank()) {
            products = productRepository.findAll(page, size);
        } else {
            products = productRepository.findByNameContainingIgnoreCase(page, size, name);
        }

        logger.info("Successfully got {} products at page {}", products.getNumberOfElements(), page);
        return products.map(productMapper::toSummaryDto);
    }

    @Override
    public DetailedProductDto createProduct(ProductCreationRequest request) {
        logger.debug("Attempt to create product");

        Product product = productMapper.toProduct(request);
        Product newProduct = productRepository.save(product);

        logger.info("Successfully created product");
        return productMapper.toDetailedDto(newProduct);
    }

    @Override
    public ProductUpdateResult updateProduct(String id, ProductUpdateRequest request) {
        logger.debug("Attempt to update '{}' product", id);

        boolean productUpdated = productRepository.updateNameAndDescription(id, request.name(), request.description());

        logger.info("Successfully updated '{}' product", id);
        return new ProductUpdateResult(productUpdated);
    }

    @Override
    public ProductUpdateResult addToCategory(String id, String categoryId) {
        logger.debug("Attempt to add '{}' product to '{}' category", id, categoryId);

        Product product = productRepository.findById(id);
        if (product.getCategory() != null) {
            throw new CategoryAssignedException("Product already assigned to category");
        }
        boolean categoryUpdated = productRepository.updateCategory(id, categoryId);

        logger.info("Successfully added '{}' product to '{}' category", id, categoryId);
        return new ProductUpdateResult(categoryUpdated);
    }

    @Override
    public ProductUpdateResult removeFromCategory(String id) {
        logger.debug("Attempt to remove '{}' product from category", id);

        Product product = productRepository.findById(id);
        if (product.getCategory() == null) {
            throw new CategoryAssignedException("Product not assigned to category");
        }
        boolean categoryUpdated = productRepository.resetCategory(id);

        logger.info("Successfully removed '{}' product from category", id);
        return new ProductUpdateResult(categoryUpdated);
    }

    @Override
    public ProductDeletionResult deleteProduct(String id) {
        logger.debug("Attempt to delete '{}' product", id);

        Product product = productRepository.findById(id);

        stockService.deleteStock(product.getStock().getId());
        productRepository.deleteById(id);

        logger.info("Successfully deleted '{}' product", id);
        return new ProductDeletionResult(true);
    }
}
