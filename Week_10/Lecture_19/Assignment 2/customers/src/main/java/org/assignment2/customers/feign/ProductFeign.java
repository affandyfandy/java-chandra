package org.assignment2.customers.feign;

import java.util.List;

import org.assignment2.customers.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductFeign {
    @GetMapping("/products/customer/{customerId}")
    List<ProductDTO> getProductsByCustomerId(@PathVariable("customerId") Long customerId);
}
