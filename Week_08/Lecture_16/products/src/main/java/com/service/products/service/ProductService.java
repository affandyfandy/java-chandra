package com.service.products.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.service.products.dto.ProductDTO;
import com.service.products.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getProductsByCustomerId(Long customerId) {
        return productRepository.findByCustomerId(customerId).stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }
}
