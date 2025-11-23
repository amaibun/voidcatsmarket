package com.amaibun.voidcatsmarket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private Long productId;
    private String title;
    private String description;
    private Long categoryId;
    private double price;
    private String currency;
    private int stock;
    private String manufacturer;
}
