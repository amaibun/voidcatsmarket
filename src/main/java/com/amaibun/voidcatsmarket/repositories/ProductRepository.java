package com.amaibun.voidcatsmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaibun.voidcatsmarket.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

}
