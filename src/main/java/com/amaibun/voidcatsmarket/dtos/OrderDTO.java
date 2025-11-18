package com.amaibun.voidcatsmarket.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    private Long orderId;
    private CustomerDTO customer;
    private List<OrderItemDTO> items;
    private String status;
}
