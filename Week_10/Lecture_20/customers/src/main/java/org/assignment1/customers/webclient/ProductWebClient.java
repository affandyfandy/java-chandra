package org.assignment1.customers.webclient;

import lombok.AllArgsConstructor;
import org.assignment1.customers.dto.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@AllArgsConstructor

public class ProductWebClient {
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
