package org.assignment1.customers.feign;

import org.assignment1.customers.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductFeign {
    @GetMapping("/products/customer/{customerId}")
    List<ProductDTO> getProductsByCustomerId(@PathVariable("customerId") Long customerId);
}
