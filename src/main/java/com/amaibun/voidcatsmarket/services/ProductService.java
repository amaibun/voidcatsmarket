package com.amaibun.voidcatsmarket.services;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import java.util.List;

public interface ProductService {
  public ProductDTO createProduct(ProductDTO product);

  public ProductDTO getProduct(Long productId);

  public ProductDTO updateProduct(Long productId, ProductDTO product);

  public void deleteProduct(Long productId);

  public List<ProductDTO> getAllProducts();
}
