package com.amaibun.voidcatsmarket.repositories;

import com.amaibun.voidcatsmarket.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
