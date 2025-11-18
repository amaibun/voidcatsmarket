package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amaibun.voidcatsmarket.dtos.OrderItemDTO;
import com.amaibun.voidcatsmarket.models.OrderItem;

@Mapper(componentModel="spring")
public interface OrderItemMapper {
    @Mapping(source = "orderItemId", target = "orderItemId")
    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);

    @Mapping(source = "orderItemId", target = "orderItemId")
    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDto);
}
