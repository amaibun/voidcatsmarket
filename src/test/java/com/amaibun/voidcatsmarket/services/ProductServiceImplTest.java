package com.amaibun.voidcatsmarket.services;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.amaibun.voidcatsmarket.AbstractIT;
import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.mappers.ProductMapper;
import com.amaibun.voidcatsmarket.models.Product;
import com.amaibun.voidcatsmarket.repositories.ProductRepository;
import com.amaibun.voidcatsmarket.services.impl.ProductServiceImpl;

class ProductServiceImplTest extends AbstractIT {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setProductId(1L);
        product.setTitle("Test Product");

        productDto = new ProductDTO();
        productDto.setProductId(1L);
        productDto.setTitle("Test Product");
    }

    @Test
    void createProduct_success() {
        when(productMapper.productDTOToProduct(any())).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.productToProductDTO(product)).thenReturn(productDto);

        ProductDTO result = productService.createProduct(productDto);

        assertEquals(productDto.getProductId(), result.getProductId());
        verify(productRepository).save(product);
    }

    @Test
    void getProduct_success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.productToProductDTO(product)).thenReturn(productDto);

        ProductDTO result = productService.getProduct(1L);

        assertEquals(productDto.getProductId(), result.getProductId());
    }

    @Test
    void updateProduct_success() {
        ProductDTO updatedDto = new ProductDTO();
        updatedDto.setTitle("Updated Product");
        updatedDto.setProductId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.productToProductDTO(product)).thenReturn(productDto);
        when(productMapper.productDTOToProduct(any())).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        ProductDTO result = productService.updateProduct(1L, updatedDto);

        assertEquals(productDto.getProductId(), result.getProductId());
        verify(productRepository).save(product);
    }

    @Test
    void deleteProduct_success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertDoesNotThrow(() -> productService.deleteProduct(1L));
        verify(productRepository).delete(product);
    }

    @Test
    void getAllProducts_success() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.productToProductDTO(product)).thenReturn(productDto);

        List<ProductDTO> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals(productDto.getProductId(), result.get(0).getProductId());
    }
}
