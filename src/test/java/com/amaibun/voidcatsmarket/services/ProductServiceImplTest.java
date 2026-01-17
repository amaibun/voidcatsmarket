package com.amaibun.voidcatsmarket.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.mappers.ProductMapper;
import com.amaibun.voidcatsmarket.models.Category;
import com.amaibun.voidcatsmarket.models.Product;
import com.amaibun.voidcatsmarket.repositories.CategoryRepository;
import com.amaibun.voidcatsmarket.repositories.ProductRepository;
import com.amaibun.voidcatsmarket.services.impl.ProductServiceImpl;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDto;
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(10L);
        category.setName("Electronics");

        product = new Product();
        product.setProductId(1L);
        product.setTitle("Test Product");
        product.setCategory(category);
        product.setDescription("Description");
        product.setPrice(BigDecimal.valueOf(100));
        product.setCurrency("USD");
        product.setStock(10);
        product.setManufacturer("TestManu");

        productDto = new ProductDTO();
        productDto.setProductId(1L);
        productDto.setTitle("Test Product");
        productDto.setDescription("Description");
        productDto.setCategoryId(10L);
        productDto.setPrice(BigDecimal.valueOf(100));
        productDto.setCurrency("USD");
        productDto.setStock(10);
        productDto.setManufacturer("TestManu");
    }

    @Test
    void create_success() {
        when(categoryRepository.findById(10L)).thenReturn(Optional.of(category));
        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDTO result = productService.create(productDto);

        assertEquals(productDto.getProductId(), result.getProductId());
        assertEquals(productDto.getTitle(), result.getTitle());
        verify(productRepository).save(product);
    }

    @Test
    void create_categoryNotFound() {
        // Test for category not found scenario
        when(categoryRepository.findById(10L)).thenReturn(Optional.empty());

        // Expecting an exception to be thrown
        assertThrows(EntityNotFoundException.class, () -> productService.create(productDto));
    }

    @Test
    void get_success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDTO result = productService.get(1L);

        assertEquals(productDto.getProductId(), result.getProductId());
        verify(productRepository).findById(1L);
    }

    @Test
    void get_productNotFound() {
        // Test for product not found scenario
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Expecting an exception to be thrown
        assertThrows(EntityNotFoundException.class, () -> productService.get(1L));
    }

    @Test
    void update_success() {
        ProductDTO updatedDto = new ProductDTO();
        updatedDto.setProductId(1L);
        updatedDto.setTitle("Updated");
        updatedDto.setDescription("Updated Desc");
        updatedDto.setCategoryId(10L);
        updatedDto.setPrice(BigDecimal.valueOf(150));
        updatedDto.setCurrency("USD");
        updatedDto.setStock(5);
        updatedDto.setManufacturer("NewManu");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(10L)).thenReturn(Optional.of(category));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(updatedDto);

        ProductDTO result = productService.update(1L, updatedDto);

        assertEquals(updatedDto.getTitle(), result.getTitle());
        assertEquals(updatedDto.getPrice(), result.getPrice());
        verify(productRepository).save(product);
    }

    @Test
    void update_productNotFound() {
        // Test for product not found scenario
        ProductDTO updatedDto = new ProductDTO();
        updatedDto.setProductId(1L);
        updatedDto.setTitle("Updated");
        updatedDto.setDescription("Updated Desc");
        updatedDto.setCategoryId(10L);

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Expecting an exception to be thrown
        assertThrows(EntityNotFoundException.class, () -> productService.update(1L, updatedDto));
    }

    @Test
    void delete_success() {
        // No need to mock findById since deleteById directly uses the ID
        doNothing().when(productRepository).deleteById(1L);

        assertDoesNotThrow(() -> productService.delete(1L));

        // Verify deleteById(1L) was called
        verify(productRepository).deleteById(1L);
    }

    @Test
    void getAll_success() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        List<ProductDTO> result = productService.getAll();

        assertEquals(1, result.size());
        assertEquals(productDto.getProductId(), result.get(0).getProductId());
        verify(productRepository).findAll();
    }

    @Test
    void getAll_emptyList() {
        // Test for when there are no products
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductDTO> result = productService.getAll();

        assertTrue(result.isEmpty());
        verify(productRepository).findAll();
    }
}
