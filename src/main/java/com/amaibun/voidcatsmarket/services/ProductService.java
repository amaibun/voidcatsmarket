package com.amaibun.voidcatsmarket.services;

import java.util.List;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;

public interface ProductService {
  public ProductDTO create(ProductDTO product);

  public ProductDTO get(Long productId);

  public ProductDTO update(Long productId, ProductDTO product);

  public void delete(Long productId);

  public List<ProductDTO> getAll();
}
