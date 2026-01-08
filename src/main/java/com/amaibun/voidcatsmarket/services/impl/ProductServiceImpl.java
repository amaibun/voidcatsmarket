package com.amaibun.voidcatsmarket.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.mappers.ProductMapper;
import com.amaibun.voidcatsmarket.models.Category;
import com.amaibun.voidcatsmarket.models.Product;
import com.amaibun.voidcatsmarket.repositories.CategoryRepository;
import com.amaibun.voidcatsmarket.repositories.ProductRepository;
import com.amaibun.voidcatsmarket.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired private ProductRepository productRepository;
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private ProductMapper productMapper;

  @Override
  public ProductDTO create(ProductDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Category with id " + dto.getCategoryId() + " was not found"));

        Product product = productMapper.toEntity(dto, category);

        Product saved = productRepository.save(product);

        return productMapper.toDto(saved);
    }

  @Override
  public ProductDTO get(Long id) {
    ProductDTO product =
        productMapper.toDto(productRepository.findById(id).get());
    if (Objects.isNull(product)) {
      throw new EntityNotFoundException("Product with id " + id + " was not found.");
    }
    return product;
  }

  @Override
  public ProductDTO update(Long id, ProductDTO dto) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Product with id " + id + " was not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Category with id " + dto.getCategoryId() + " was not found"));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setCategory(category);
        existing.setPrice(dto.getPrice());
        existing.setCurrency(dto.getCurrency());
        existing.setStock(dto.getStock());
        existing.setManufacturer(dto.getManufacturer());

        return productMapper.toDto(productRepository.save(existing));
    }

  @Override
  public void delete(Long id) {
    Product existingProduct = productRepository.findById(id).get();
    if (!Objects.isNull(existingProduct)) {
      productRepository.delete(existingProduct);
    }
  }

  @Override
  public List<ProductDTO> getAll() {
    List<Product> products = productRepository.findAll();
    List<ProductDTO> dtos =
        products.stream()
            .map(product -> productMapper.toDto(product))
            .collect(Collectors.toList());

    return dtos;
  }
}
