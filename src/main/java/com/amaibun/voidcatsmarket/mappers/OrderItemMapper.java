package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.amaibun.voidcatsmarket.dtos.OrderItemDTO;
import com.amaibun.voidcatsmarket.models.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "order.orderId", target = "orderId")
    OrderItemDTO toDto(OrderItem orderItem);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    void updateEntity(OrderItemDTO dto, @MappingTarget OrderItem orderItem);
}
