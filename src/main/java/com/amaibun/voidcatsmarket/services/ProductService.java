package com.amaibun.voidcatsmarket.services;

import java.util.List;

import com.amaibun.voidcatsmarket.models.Product;

public interface ProductService {
    public Product createProduct(Product product);

    public Product getProduct(String productId);

    public Product updateProduct(String productId, Product product);

    public void deleteProduct(String productId);

    public List<Product> getAllProducts();
}
