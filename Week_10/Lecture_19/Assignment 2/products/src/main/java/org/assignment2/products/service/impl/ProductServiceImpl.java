package org.assignment2.products.service.impl;

import lombok.AllArgsConstructor;
import org.assignment2.products.dto.ProductDTO;
import org.assignment2.products.entity.Product;
import org.assignment2.products.repository.ProductRepository;
import org.assignment2.products.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl  implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product = productRepository.save(product);
        productDTO.setId(product.getId());
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        BeanUtils.copyProperties(productDTO, product, "id");
        product = productRepository.save(product);
        return productDTO;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            return productDTO;
        }).collect(Collectors.toList());
    }
    public List<ProductDTO> getProductsByCustomerId(Long customerId) {
        return productRepository.findByCustomerId(customerId).stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }
}
