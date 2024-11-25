package dev.jakubdacewicz.product_service.product;

import dev.jakubdacewicz.product_service.product.dto.DetailedProductDto;
import dev.jakubdacewicz.product_service.product.dto.SummaryProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class DefaultProductService implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultProductService.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    DefaultProductService(ProductRepository productRepository,
                          ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
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
}
