package dev.jakubdacewicz.product_service.category;

import dev.jakubdacewicz.product_service.category.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
class DefaultCategoryService implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCategoryService.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    DefaultCategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public SummaryCategoryDto getCategory(String id) {
        logger.debug("Attempt to get '{}' summary category", id);

        Category category = categoryRepository.findById(id);

        logger.info("Successfully got '{}' summary category", id);
        return categoryMapper.toSummaryDto(category);
    }

    @Override
    public DetailedCategoryDto getCategoryDetails(String id) {
        logger.debug("Attempt to get '{}' detailed category", id);

        long productCount = categoryRepository.countProducts(id);
        Category category = categoryRepository.findById(id);

        logger.info("Successfully got '{}' detailed category", id);
        return categoryMapper.toDetailedDto(category, productCount);
    }

    @Override
    public Page<SummaryCategoryDto> getCategories(int page, int size, String name) {
        return null;
    }

    @Override
    public SummaryCategoryDto createCategory(CategoryCreationRequest request) {
        logger.debug("Attempt to create '{}' category", request.name());

        Category category = categoryMapper.toCategory(request);
        Category newCategory = categoryRepository.save(category);

        logger.info("Successfully created '{}' category", request.name());
        return categoryMapper.toSummaryDto(newCategory);
    }

    @Override
    public DetailedCategoryDto updateCategory(String id, CategoryUpdateRequest request) {
        return null;
    }

    @Override
    public DetailedCategoryDto updateCategoryEnable(String id, boolean enabled) {
        return null;
    }

    @Override
    public CategoryDeletionResult deleteCategory(String id) {
        return null;
    }
}
