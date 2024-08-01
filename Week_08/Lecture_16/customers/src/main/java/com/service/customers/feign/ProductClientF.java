package com.service.customers.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.service.customers.dto.ProductDTO;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClientF {
    @GetMapping("/products/customer/{customerId}")
    List<ProductDTO> getProductsByCustomerId(@PathVariable("customerId") Long customerId);
}
