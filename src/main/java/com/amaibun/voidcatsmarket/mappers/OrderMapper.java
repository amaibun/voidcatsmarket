package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amaibun.voidcatsmarket.dtos.OrderDTO;
import com.amaibun.voidcatsmarket.models.Order;

@Mapper(componentModel="spring")
public interface OrderMapper {
    @Mapping(source = "orderId", target = "orderId")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "orderId", target = "orderId")
    Order orderDTOToOrder(OrderDTO orderDto);
}
