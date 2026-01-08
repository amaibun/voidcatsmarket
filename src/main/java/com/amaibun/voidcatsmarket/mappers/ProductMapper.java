package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.models.Category;
import com.amaibun.voidcatsmarket.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductDTO toDto(Product product);

    @Mapping(source = "category", target = "category")
    Product toEntity(ProductDTO dto, Category category);
}