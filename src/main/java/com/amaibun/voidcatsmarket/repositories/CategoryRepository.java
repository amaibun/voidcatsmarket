package com.amaibun.voidcatsmarket.repositories;

import com.amaibun.voidcatsmarket.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
