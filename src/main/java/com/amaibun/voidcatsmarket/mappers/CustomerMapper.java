package com.amaibun.voidcatsmarket.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.amaibun.voidcatsmarket.dtos.CustomerDTO;
import com.amaibun.voidcatsmarket.models.Customer;
import com.amaibun.voidcatsmarket.models.Order;
import com.amaibun.voidcatsmarket.repositories.OrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {
  @Autowired private OrderRepository orderRepository;

  @Mapping(source = "orders", target = "orderIds", qualifiedByName = "mapOrderToOrderId")
  public abstract CustomerDTO customerToCustomerDTO(Customer customer);

  @Mapping(source = "orderIds", target = "orders", qualifiedByName = "mapOrderIdToOrder")
  public abstract Customer customerDTOToCustomer(CustomerDTO customerDto);

  @Named("mapOrderToOrderId")
  public List<Long> mapOrderToOrderId(List<Order> orders) {
    ArrayList<Long> orderIds = new ArrayList<>();

    for (Order order : orders) {
      orderIds.add(order.getOrderId());
    }

    return orderIds;
  }

  // Will try to reduce code duplication among mappers in the future
  @Named("mapOrderIdToOrder")
  public List<Order> mapOrderIdToOrder(List<Long> orderIds) {
    ArrayList<Order> orders = new ArrayList<>();

    for (Long orderId : orderIds) {
      orders.add(
          orderRepository
              .findById(orderId)
              .orElseThrow(
                  () ->
                      new EntityNotFoundException("Order with id " + orderId + " was not found")
                    )
                );
    }

    return orders;
  }
}
