package com.amaibun.voidcatsmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaibun.voidcatsmarket.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
