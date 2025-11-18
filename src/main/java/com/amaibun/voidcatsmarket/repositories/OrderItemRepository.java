package com.amaibun.voidcatsmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaibun.voidcatsmarket.models.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
