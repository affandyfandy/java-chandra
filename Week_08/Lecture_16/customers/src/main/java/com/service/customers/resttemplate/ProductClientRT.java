package com.service.customers.resttemplate;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.service.customers.dto.ProductDTO;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductClientRT {

    private final RestTemplate restTemplate;
    private static final String PRODUCT_SERVICE_URL = "http://localhost:8081/products/customer/";

    public List<ProductDTO> getProductsByCustomerId(Long customerId) {
        ProductDTO[] products = restTemplate.getForObject(PRODUCT_SERVICE_URL + customerId, ProductDTO[].class);
        return Arrays.asList(products);
    }
}