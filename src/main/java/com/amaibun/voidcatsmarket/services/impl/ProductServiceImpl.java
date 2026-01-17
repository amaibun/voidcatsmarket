package com.amaibun.voidcatsmarket.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.mappers.ProductMapper;
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

        Product product = productMapper.toEntity(dto);

        product.setCategory(
                categoryRepository.findById(dto.getCategoryId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Category with id " + dto.getCategoryId() + " was not found"))
        );

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDTO get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Product with id " + id + " was not found"));

        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Product with id " + id + " was not found"));

        productMapper.updateEntity(dto, existing);

        if (dto.getCategoryId() != null) {
            existing.setCategory(
                    categoryRepository.findById(dto.getCategoryId())
                            .orElseThrow(() ->
                                    new EntityNotFoundException(
                                            "Category with id " + dto.getCategoryId() + " was not found"))
            );
        }

        return productMapper.toDto(productRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
