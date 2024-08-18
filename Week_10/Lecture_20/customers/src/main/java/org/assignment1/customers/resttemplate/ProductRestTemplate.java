package org.assignment1.customers.resttemplate;

import lombok.AllArgsConstructor;
import org.assignment1.customers.dto.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductRestTemplate {
    private final RestTemplate restTemplate;
    private static final String PRODUCT_SERVICE_URL = "http://localhost:8081/products/customer/";

    public List<ProductDTO> getProductsByCustomerId(Long customerId) {
        ProductDTO[] products = restTemplate.getForObject(PRODUCT_SERVICE_URL + customerId, ProductDTO[].class);
        return Arrays.asList(products);
    }
}
