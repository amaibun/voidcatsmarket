package com.amaibun.voidcatsmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaibun.voidcatsmarket.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
