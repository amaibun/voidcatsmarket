package com.amaibun.voidcatsmarket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDTO {
    private Long orderItemId;
    private ProductDTO product;
    private String quantity;
    private OrderDTO order;
}
