package com.assignment2.assignment2.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assignment2.assignment2.filter.ApiKeyFilter;
import com.assignment2.assignment2.repository.ApiRepository;

@Configuration
public class FilterConfig {

    private final ApiRepository apiRepository;

    public FilterConfig(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter() {
        FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiKeyFilter(apiRepository));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
