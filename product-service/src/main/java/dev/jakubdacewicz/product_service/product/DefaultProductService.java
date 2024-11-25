package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.product.dto.DetailedProductDto;
import dev.jakubdacewicz.product_service.product.dto.ProductCreationRequest;
import dev.jakubdacewicz.product_service.product.dto.SummaryProductDto;
import dev.jakubdacewicz.product_service.stock.StockService;
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

    private final StockService service;

    DefaultProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          StockService service) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.service = service;
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
}
