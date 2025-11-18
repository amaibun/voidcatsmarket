package com.amaibun.voidcatsmarket.services;

import java.util.List;

import com.amaibun.voidcatsmarket.models.Category;

public interface CategoryService {
    public Category createCategory(Category category);

    public Category getCategory(Long categoryId);

    public Category updateCategory(Long categoryId, Category category);

    public void deleteCategory(Long categoryId);

    public List<Category> getAllCategories();
}
