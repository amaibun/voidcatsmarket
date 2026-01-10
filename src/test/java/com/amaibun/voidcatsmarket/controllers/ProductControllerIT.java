package com.amaibun.voidcatsmarket.controllers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.amaibun.voidcatsmarket.AbstractIT;
import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.models.Category;
import com.amaibun.voidcatsmarket.models.Product;
import com.amaibun.voidcatsmarket.repositories.CategoryRepository;
import com.amaibun.voidcatsmarket.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ProductControllerIT extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category createCategory() {
        Category category = new Category();
        category.setName("Test Category");
        return categoryRepository.save(category);
    }

    private Product createValidProduct(Category category) {
        Product product = new Product();
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(10.0));
        product.setCurrency("USD");
        product.setStock(5);
        product.setCategory(category);
        return product;
    }

    private ProductDTO createValidProductDTO(Category category) {
        ProductDTO dto = new ProductDTO();
        dto.setTitle("Test Product");
        dto.setDescription("Test Description");
        dto.setPrice(BigDecimal.valueOf(20.0));
        dto.setCurrency("USD");
        dto.setStock(10);
        dto.setCategoryId(category.getCategoryId());
        return dto;
    }

    @Test
    void getAll_returns200_andProducts() throws Exception {
        Category category = createCategory();
        productRepository.save(createValidProduct(category));

        mockMvc.perform(get("/api/v1/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Test Product"))
            .andExpect(jsonPath("$[0].categoryId").value(category.getCategoryId()));
    }

    @Test
    void getById_returns200() throws Exception {
        Category category = createCategory();
        Product saved = productRepository.save(createValidProduct(category));

        mockMvc.perform(get("/api/v1/products/{id}", saved.getProductId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Test Product"))
            .andExpect(jsonPath("$.categoryId").value(category.getCategoryId()));
    }

    @Test
    void create_returns201_andPersists() throws Exception {
        Category category = createCategory();
        ProductDTO dto = createValidProductDTO(category);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("Test Product"));

        assertEquals(1, productRepository.count());
    }

    @Test
    void update_returns200_andUpdates() throws Exception {
        Category category = createCategory();
        Product saved = productRepository.save(createValidProduct(category));

        ProductDTO updateDto = createValidProductDTO(category);
        updateDto.setTitle("Updated Product");

        mockMvc.perform(put("/api/v1/products/{id}", saved.getProductId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Updated Product"));
    }

    @Test
    void delete_returns200_andDeletes() throws Exception {
        Category category = createCategory();
        Product saved = productRepository.save(createValidProduct(category));

        mockMvc.perform(delete("/api/v1/products/{id}", saved.getProductId()))
            .andExpect(status().isOk());

        assertFalse(productRepository.findById(saved.getProductId()).isPresent());
    }
}
