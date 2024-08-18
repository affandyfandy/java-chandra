package org.assignment1.customers.config;

import org.assignment1.customers.interceptor.ClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final ClientInterceptor clientInterceptor;

    @Bean
    public ClientInterceptor feignClientInterceptor() {
        return new ClientInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientInterceptor).addPathPatterns("/customers/**");
    }
}
