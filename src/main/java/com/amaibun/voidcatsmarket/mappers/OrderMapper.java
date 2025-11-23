package com.amaibun.voidcatsmarket.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.amaibun.voidcatsmarket.dtos.OrderDTO;
import com.amaibun.voidcatsmarket.models.Customer;
import com.amaibun.voidcatsmarket.models.Order;
import com.amaibun.voidcatsmarket.models.OrderItem;
import com.amaibun.voidcatsmarket.repositories.CustomerRepository;
import com.amaibun.voidcatsmarket.repositories.OrderItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
  @Autowired private CustomerRepository customerRepository;

  @Autowired private OrderItemRepository orderItemRepository;

  @Mapping(source = "customer", target = "customerId", qualifiedByName = "mapCustomerToCustomerId")
  @Mapping(source = "items", target = "itemIds", qualifiedByName = "mapOrderItemToOrderItemId")
  public abstract OrderDTO orderToOrderDTO(Order order);

  @Mapping(source = "customerId", target = "customer", qualifiedByName = "mapCustomerIdToCustomer")
  @Mapping(source = "itemIds", target = "items", qualifiedByName = "mapOrderItemIdToOrderItem")
  public abstract Order orderDTOToOrder(OrderDTO orderDto);

  @Named("mapCustomerToCustomerId")
  public Long mapCustomerToCustomerId(Customer customer) {
    return customer.getCustomerId();
  }

  @Named("mapCustomerIdToCustomer")
  public Customer mapCustomerIdToCustomer(Long customerId) {
    return customerRepository
        .findById(customerId)
        .orElseThrow(
            () -> new EntityNotFoundException("Customer with id " + customerId + " was not found")
        );
  }

  @Named("mapOrderItemToOrderItemId")
  public List<Long> mapOrderItemToOrderItemId(List<OrderItem> orderItems) {
    List<Long> orderItemIds = new ArrayList<>();
    for (OrderItem orderItem : orderItems) {
      orderItemIds.add(orderItem.getOrderItemId());
    }

    return orderItemIds;
  }

  @Named("mapOrderItemIdToOrderItem")
  public List<OrderItem> mapOrderItemIdToOrderItem(List<Long> orderItemIds) {
    List<OrderItem> orderItems = new ArrayList<>();
    for (Long orderItemId : orderItemIds) {
      orderItems.add(
          orderItemRepository
              .findById(orderItemId)
              .orElseThrow(
                  () ->
                      new EntityNotFoundException(
                          "Order item with id " + orderItemId + " was not found"
                        )
                    )
                );
    }

    return orderItems;
  }
}
