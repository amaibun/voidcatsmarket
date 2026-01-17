package com.amaibun.voidcatsmarket.dtos;

import java.math.BigDecimal;

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
  private BigDecimal price;
  private String currency;
  private int stock;
  private String manufacturer;
}
