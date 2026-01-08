package com.amaibun.voidcatsmarket.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amaibun.voidcatsmarket.models.OrderItem;
import com.amaibun.voidcatsmarket.repositories.projections.ProductSalesReport;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("""
        SELECT 
            p.title AS title,
            SUM(oi.quantity) AS totalSold
        FROM OrderItem oi
        JOIN oi.product p
        GROUP BY p.title
        ORDER BY totalSold DESC
    """)
    public List<ProductSalesReport> findMostPurchasedProducts();
}

