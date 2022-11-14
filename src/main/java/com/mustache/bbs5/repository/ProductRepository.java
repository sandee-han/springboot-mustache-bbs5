package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
