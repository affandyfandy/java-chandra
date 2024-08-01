package com.service.customers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.service.customers.interceptor.ClientInterceptor;

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
