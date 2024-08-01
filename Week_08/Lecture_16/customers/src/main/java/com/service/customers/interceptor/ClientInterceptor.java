package com.service.customers.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ClientInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ClientInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Log the request details
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String completeURL = requestURI + (queryString != null ? "?" + queryString : "");

        logger.info("Incoming request: [{}] {}", method, completeURL);

        // Set the header
        response.setHeader("Microservices", "Customer-Products");

        return true;
    }
}