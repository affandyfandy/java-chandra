package org.assignment2.products.service;

import java.util.List;

import org.assignment2.products.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> getProductsByCustomerId(Long customerId);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> getAllProducts();
}
