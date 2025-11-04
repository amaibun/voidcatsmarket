package com.amaibun.voidcatsmarket.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaibun.voidcatsmarket.models.Product;
import com.amaibun.voidcatsmarket.repositories.ProductRepository;
import com.amaibun.voidcatsmarket.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(String productId) {
        Product product = productRepository.findById(productId).get();
        if (Objects.isNull(product)) {
            throw new EntityNotFoundException("Product with id " + productId + " was not found.");
        }
        return product;
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        Product existingProduct = getProduct(productId);

        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCurrency(product.getCurrency());
        existingProduct.setStock(product.getStock());
        existingProduct.setManufacturer(product.getManufacturer());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        Product existingProduct = productRepository.findById(productId).get();
        if (!Objects.isNull(existingProduct)) {
            productRepository.delete(existingProduct);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
