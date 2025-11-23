package com.amaibun.voidcatsmarket.mappers;

import com.amaibun.voidcatsmarket.dtos.CategoryDTO;
import com.amaibun.voidcatsmarket.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
  @Mapping(source = "categoryId", target = "categoryId")
  public abstract CategoryDTO categoryToCategoryDTO(Category category);

  // Don't know how to deal with ommited fields. Will implement this mapper later
  @Mapping(source = "categoryId", target = "categoryId")
  public abstract Category categoryDTOtoCategory(CategoryDTO categoryDto);
}
