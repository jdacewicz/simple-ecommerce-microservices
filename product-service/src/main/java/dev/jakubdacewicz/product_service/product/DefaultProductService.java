package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.category.CategoryService;
import dev.jakubdacewicz.product_service.category.dto.CategoryUpdateResult;
import dev.jakubdacewicz.product_service.product.dto.*;
import dev.jakubdacewicz.product_service.shared.exception.CategoryAssignedException;
import dev.jakubdacewicz.product_service.stock.StockService;
import dev.jakubdacewicz.product_service.stock.dto.StockCreationRequest;
import dev.jakubdacewicz.product_service.stock.dto.StockDto;
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
    private final CategoryService categoryService;

    DefaultProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          StockService stockService,
                          CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.stockService = stockService;
        this.categoryService = categoryService;
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
    public SummaryProductDto createProduct(ProductCreationRequest request) {
        logger.debug("Attempt to create product");

        StockCreationRequest stockCreationRequest = productMapper.toStockCreationRequest(request);
        StockDto stock = stockService.createStock(stockCreationRequest);

        Product product = productMapper.toProduct(request, stock);
        Product newProduct = productRepository.save(product);

        String categoryId = request.categoryId();
        if (categoryId != null && !categoryId.isBlank()) {
            categoryService.addProductToCategory(categoryId, newProduct.getId());
        }

        logger.info("Successfully created product");
        return productMapper.toSummaryDto(newProduct);
    }

    @Override
    public ProductUpdateResult updateProduct(String id, ProductUpdateRequest request) {
        logger.debug("Attempt to update '{}' product", id);

        boolean productUpdated = productRepository.updateNameAndDescription(id, request.name(), request.description());

        logger.info("Successfully updated '{}' product", id);
        return new ProductUpdateResult(productUpdated);
    }

    @Override
    public ProductCategoryUpdateResult addToCategory(String id, String categoryId) {
        logger.debug("Attempt to add '{}' product to '{}' category", id, categoryId);

        Product product = productRepository.findById(id);
        if (product.getCategory() != null) {
            throw new CategoryAssignedException("Product already assigned to category");
        }
        CategoryUpdateResult categoryUpdateResult = categoryService.addProductToCategory(categoryId, id);
        boolean productUpdated = productRepository.updateCategory(id, categoryId);

        logger.info("Successfully added '{}' product to '{}' category", id, categoryId);
        return productMapper.toProductCategoryUpdateResult(categoryUpdateResult, productUpdated);
    }

    @Override
    public ProductCategoryUpdateResult removeFromCategory(String id) {
        logger.debug("Attempt to remove '{}' product from category", id);

        Product product = productRepository.findById(id);
        if (product.getCategory() == null) {
            throw new CategoryAssignedException("Product not assigned to category");
        }
        CategoryUpdateResult categoryUpdateResult =
                categoryService.removeProductFromCategory(product.getCategory().getId(), id);
        boolean productUpdated = productRepository.resetCategory(id);

        logger.info("Successfully removed '{}' product from category", id);
        return productMapper.toProductCategoryUpdateResult(categoryUpdateResult, productUpdated);
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
