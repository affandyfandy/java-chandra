package org.assignment1.products.service;

import org.assignment1.products.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProductsByCustomerId(Long customerId);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> getAllProducts();
}
