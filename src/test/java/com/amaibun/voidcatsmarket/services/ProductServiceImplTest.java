package com.amaibun.voidcatsmarket.services;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

        product = new Product();
        product.setProductId(1L);
        product.setTitle("Test Product");
        product.setCategory(category);

        productDto = new ProductDTO();
        productDto.setProductId(1L);
        productDto.setTitle("Test Product");
        productDto.setCategoryId(10L);
    }


    @Test
    void create_success() {

        when(categoryRepository.findById(10L))
                .thenReturn(Optional.of(category));

        when(productMapper.toEntity(any(), eq(category)))
                .thenReturn(product);

        when(productRepository.save(product))
                .thenReturn(product);

        when(productMapper.toDto(product))
                .thenReturn(productDto);

        ProductDTO result = productService.create(productDto);

        assertEquals(1L, result.getProductId());
    }

    @Test
    void get_success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDTO result = productService.get(1L);

        assertEquals(productDto.getProductId(), result.getProductId());
    }

    @Test
    void update_success() {

        ProductDTO updatedDto = new ProductDTO();
        updatedDto.setProductId(1L);
        updatedDto.setTitle("Updated");
        updatedDto.setCategoryId(10L);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(categoryRepository.findById(10L))
                .thenReturn(Optional.of(category));

        when(productRepository.save(product))
                .thenReturn(product);

        when(productMapper.toDto(product))
                .thenReturn(productDto);

        ProductDTO result = productService.update(1L, updatedDto);

        assertEquals(1L, result.getProductId());
    }

    @Test
    void delete_success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertDoesNotThrow(() -> productService.delete(1L));
        verify(productRepository).delete(product);
    }

    @Test
    void getAll_success() {

        when(productRepository.findAll())
                .thenReturn(List.of(product));

        when(productMapper.toDto(product))
                .thenReturn(productDto);

        List<ProductDTO> result = productService.getAll();

        assertEquals(1, result.size());
    }
}
