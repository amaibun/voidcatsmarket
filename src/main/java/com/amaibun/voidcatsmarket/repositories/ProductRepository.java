package com.amaibun.voidcatsmarket.repositories;

import com.amaibun.voidcatsmarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
