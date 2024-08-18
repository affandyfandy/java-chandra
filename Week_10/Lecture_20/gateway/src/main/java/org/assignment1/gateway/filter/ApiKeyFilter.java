package org.assignment1.gateway.filter;

import org.assignment1.gateway.config.ApiKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ApiKeyFilter extends AbstractGatewayFilterFactory<ApiKeyConfig> {


    private final WebClient.Builder webClientBuilder;

    @Autowired
    public ApiKeyFilter(WebClient.Builder webClientBuilder) {
        super(ApiKeyConfig.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(ApiKeyConfig config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            String apiKey = headers.getFirst(config.getApiKeyHeaderName());

            if (apiKey == null || apiKey.isEmpty()) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClientBuilder.build()
                    .get()
                    .uri(config.getAuthServiceUrl() + "?apiKey=" + apiKey)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .flatMap(isValid -> {
                        if (Boolean.TRUE.equals(isValid)) {
                            return chain.filter(exchange);
                        } else {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                    });
        };
    }
}