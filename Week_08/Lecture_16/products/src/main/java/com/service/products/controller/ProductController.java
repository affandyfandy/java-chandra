package com.service.products.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.products.dto.ProductDTO;
import com.service.products.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(productService.getProductsByCustomerId(customerId));
    }
}
