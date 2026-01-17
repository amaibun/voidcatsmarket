package com.amaibun.voidcatsmarket.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.amaibun.voidcatsmarket.dtos.CustomerDTO;
import com.amaibun.voidcatsmarket.models.Customer;
import com.amaibun.voidcatsmarket.models.Order;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "orders", target = "orderIds")
    CustomerDTO toDto(Customer customer);

    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CustomerDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "orders", ignore = true)
    void updateEntity(CustomerDTO dto, @MappingTarget Customer customer);

    default List<Long> mapOrdersToIds(List<Order> orders) {
        if (orders == null) return List.of();
        return orders.stream()
                .map(Order::getOrderId)
                .toList();
    }
}