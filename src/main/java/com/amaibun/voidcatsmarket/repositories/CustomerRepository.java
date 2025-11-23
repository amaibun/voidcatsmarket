package com.amaibun.voidcatsmarket.repositories;

import com.amaibun.voidcatsmarket.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
