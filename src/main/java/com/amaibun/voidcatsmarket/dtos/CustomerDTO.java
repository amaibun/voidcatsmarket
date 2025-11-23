package com.amaibun.voidcatsmarket.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Long> orderIds;
}
