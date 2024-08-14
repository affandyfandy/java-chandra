package org.assignment2.products.repository;

import java.util.List;

import org.assignment2.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCustomerId(Long customerId);
}