package com.assignment1.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.assignment1.security.dto.ProductDTO;
import java.util.Optional;

public interface ProductService {

    Page<ProductDTO> getAllProducts(Pageable pageable);

    Optional<ProductDTO> getProductById(Long id);

    ProductDTO createProduct(ProductDTO productDTO);

    Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}
