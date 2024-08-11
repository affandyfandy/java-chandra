package org.assignment1.midtest.repository;

import org.assignment1.midtest.entity.Product;
import org.assignment1.midtest.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByStatus(Status status, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseAndStatus(String name, Status status, Pageable pageable);
}
