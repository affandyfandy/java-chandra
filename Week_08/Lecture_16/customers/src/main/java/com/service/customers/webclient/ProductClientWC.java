package com.service.customers.webclient;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.service.customers.dto.ProductDTO;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ProductClientWC {
    private final WebClient webClient;

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8081/products/customer/";

    public List<ProductDTO> getProductsByCustomerId(Long customerId) {
        Mono<ProductDTO[]> productsMono = webClient.get()
                .uri(PRODUCT_SERVICE_URL + customerId)
                .retrieve()
                .bodyToMono(ProductDTO[].class);

        ProductDTO[] products = productsMono.block(); // blocking for simplicity
        return List.of(products);
    }
}
