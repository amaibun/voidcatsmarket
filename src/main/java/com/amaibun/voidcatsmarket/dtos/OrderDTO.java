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
    private Long customerId;
    private List<Long> itemIds;
    private String status;
}
