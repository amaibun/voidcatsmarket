package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amaibun.voidcatsmarket.dtos.CategoryDTO;
import com.amaibun.voidcatsmarket.models.Category;

@Mapper(componentModel="spring")
public interface CategoryMapper {
    @Mapping(source = "categoryId", target = "categoryId")
    CategoryDTO categoryToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "categoryId")
    Category categoryDTOtoCategory(CategoryDTO categoryDto);
}
