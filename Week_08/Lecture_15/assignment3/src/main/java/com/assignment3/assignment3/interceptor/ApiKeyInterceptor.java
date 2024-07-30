package com.assignment3.assignment3.interceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.assignment3.assignment3.entity.ApiKey;
import com.assignment3.assignment3.repository.ApiRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private final ApiRepository apiKeyRepository;

    @Autowired
    public ApiKeyInterceptor(ApiRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String apiKeyValue = request.getHeader("api-key");

        if (apiKeyValue == null || apiKeyValue.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing API Key");
            return false;
        }

        ApiKey apiKey = apiKeyRepository.findByKey(apiKeyValue);
        if (apiKey == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API Key");
            return false;
        }

        // Store the username in the request header
        request.setAttribute("username", apiKey.getUsername());

        // Update the last used time
        apiKey.setLastUsed(LocalDateTime.now());
        apiKeyRepository.save(apiKey);

        // Add timestamp header
        response.setHeader("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        return true;
    }
}
