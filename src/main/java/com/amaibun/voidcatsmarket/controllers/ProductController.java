package com.amaibun.voidcatsmarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.services.ProductService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDtos = productService.getAllProducts();

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        ProductDTO foundProductDto = productService.getProduct(productId);
        return ResponseEntity.ok(foundProductDto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDto) {
        ProductDTO createdProductDTO = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductDTO productDto) {
        ProductDTO updatedProductDto = productService.updateProduct(productId, productDto);
        return ResponseEntity.ok(updatedProductDto);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(null);
    }
}
