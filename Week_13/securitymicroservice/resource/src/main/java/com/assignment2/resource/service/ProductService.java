package com.assignment2.resource.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.assignment2.resource.dto.ProductDTO;

import java.util.Optional;

public interface ProductService {

    Page<ProductDTO> getAllProducts(Pageable pageable);

    Optional<ProductDTO> getProductById(Long id);

    ProductDTO createProduct(ProductDTO productDTO);

    Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}