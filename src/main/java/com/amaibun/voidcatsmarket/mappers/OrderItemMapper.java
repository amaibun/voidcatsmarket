package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.amaibun.voidcatsmarket.dtos.OrderItemDTO;
import com.amaibun.voidcatsmarket.models.Order;
import com.amaibun.voidcatsmarket.models.OrderItem;
import com.amaibun.voidcatsmarket.models.Product;
import com.amaibun.voidcatsmarket.repositories.OrderRepository;
import com.amaibun.voidcatsmarket.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Mapper(componentModel="spring")
public abstract class OrderItemMapper {
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    OrderRepository orderRepository;

    @Mapping(source="product", target="productId", qualifiedByName="mapProductToProductId")
    @Mapping(source="order", target="orderId", qualifiedByName="mapOrderToOrderId")
    public abstract OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);

    @Mapping(source="productId", target="product", qualifiedByName="mapProductIdToProduct")
    @Mapping(source="orderId", target="order", qualifiedByName="mapOrderIdToOrder")
    public abstract OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDto);

    @Named("mapProductToProductId")
    public Long mapProductToProductId(Product product) {
        return product.getProductId();
    }

    @Named("mapOrderToOrderId")
    public Long mapOrderToOrderId(Order order) {
        return order.getOrderId();
    }

    @Named("mapProductIdToProduct")
    public Product mapProductIdToProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " was not found"));
    }

    @Named("mapOrderIdToOrder")
    public Order mapOrderIdToOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " was not found"));
    }
}
