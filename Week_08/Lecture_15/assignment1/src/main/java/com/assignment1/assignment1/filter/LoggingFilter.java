package com.assignment1.assignment1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("LoggingFilter: Request received - " + request.getRequestURI());

        // Continue the filter chain
        filterChain.doFilter(request, response);

        System.out.println("LoggingFilter: Response sent - " + request.getRequestURI());
    }
}