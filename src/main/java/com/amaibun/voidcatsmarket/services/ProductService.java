package com.amaibun.voidcatsmarket.services;

import java.util.List;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;

public interface ProductService {
    public ProductDTO createProduct(ProductDTO product);

    public ProductDTO getProduct(Long productId);

    public ProductDTO updateProduct(Long productId, ProductDTO product);

    public void deleteProduct(Long productId);

    public List<ProductDTO> getAllProducts();
}
