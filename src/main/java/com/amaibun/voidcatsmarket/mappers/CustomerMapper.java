package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amaibun.voidcatsmarket.dtos.CustomerDTO;
import com.amaibun.voidcatsmarket.models.Customer;

@Mapper(componentModel="spring")
public interface CustomerMapper {
    @Mapping(source = "customerId", target = "customerId")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source = "customerId", target = "customerId")
    Customer customerDTOToCustomer(CustomerDTO customerDto);
}
