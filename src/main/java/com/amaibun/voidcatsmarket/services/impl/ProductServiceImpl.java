package com.amaibun.voidcatsmarket.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.mappers.ProductMapper;
import com.amaibun.voidcatsmarket.models.Product;
import com.amaibun.voidcatsmarket.repositories.ProductRepository;
import com.amaibun.voidcatsmarket.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        return productMapper.productToProductDTO(productRepository.save(
            productMapper.productDTOToProduct(product)
        ));
    }

    @Override
    public ProductDTO getProduct(Long productId) {
        ProductDTO product = productMapper.productToProductDTO(productRepository.findById(productId).get());
        if (Objects.isNull(product)) {
            throw new EntityNotFoundException("Product with id " + productId + " was not found.");
        }
        return product;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO product) {
        ProductDTO existingProduct = getProduct(productId);

        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCurrency(product.getCurrency());
        existingProduct.setStock(product.getStock());
        existingProduct.setManufacturer(product.getManufacturer());

        return productMapper.productToProductDTO(productRepository.save(
            productMapper.productDTOToProduct(existingProduct)
        ));
    }

    @Override
    public void deleteProduct(Long productId) {
        Product existingProduct = productRepository.findById(productId).get();
        if (!Objects.isNull(existingProduct)) {
            productRepository.delete(existingProduct);
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDtos = products.stream()
                                               .map(product -> productMapper.productToProductDTO(product))
                                               .collect(Collectors.toList());

        return productDtos;
    }
}
