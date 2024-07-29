package com.assignment2.assignment2.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.assignment2.assignment2.repository.ApiRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyFilter extends OncePerRequestFilter {

    private final ApiRepository apiKeyRepository;

    @Autowired
    public ApiKeyFilter(ApiRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String apiKey = request.getHeader("api-key");

        System.out.println("API key filter: Request received: " + request.getRequestURI());
        if (apiKey == null || apiKeyRepository.findByKey(apiKey) == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API Key");
            return;
        }
        filterChain.doFilter(request, response);

        System.out.println("API key filter: Response sent - " + request.getRequestURI());
    }
}
