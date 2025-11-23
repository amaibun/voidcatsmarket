package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.models.Category;
import com.amaibun.voidcatsmarket.models.Product;
import com.amaibun.voidcatsmarket.repositories.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Mapper(componentModel="spring")
public abstract class ProductMapper {
    @Autowired
    private CategoryRepository categoryRepository;

    @Mapping(source = "category", target = "categoryId", qualifiedByName="mapCategoryToCategoryId")
    public abstract ProductDTO productToProductDTO(Product product);

    @Mapping(source = "categoryId", target = "category", qualifiedByName="mapCategoryIdToCategory")
    public abstract Product productDTOToProduct(ProductDTO productDto);

    @Named("mapCategoryToCategoryId")
    Long mapCategoryToCategoryId(Category category) {
        return category.getCategoryId();
    }

    @Named("mapCategoryIdToCategory")
    Category mapCategoryIdToCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " was not found"));
    }
}
