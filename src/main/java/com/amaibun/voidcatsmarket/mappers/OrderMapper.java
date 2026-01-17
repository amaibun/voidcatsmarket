package com.amaibun.voidcatsmarket.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.amaibun.voidcatsmarket.dtos.OrderDTO;
import com.amaibun.voidcatsmarket.models.Order;
import com.amaibun.voidcatsmarket.models.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(source = "items", target = "itemIds")
    OrderDTO toDto(Order order);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateEntity(OrderDTO dto, @MappingTarget Order order);

    default List<Long> mapItemsToIds(List<OrderItem> items) {
        if (items == null) return List.of();
        return items.stream()
                .map(OrderItem::getOrderItemId)
                .toList();
    }
}
