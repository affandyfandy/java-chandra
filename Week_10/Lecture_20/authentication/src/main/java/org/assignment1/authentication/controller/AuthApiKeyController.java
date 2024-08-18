package org.assignment1.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.assignment1.authentication.service.impl.AuthApiKeyServiceImpl;

@RestController
@RequestMapping("/auth-api-key")
public class AuthApiKeyController {
    private final AuthApiKeyServiceImpl authService;

    @Autowired
    public AuthApiKeyController(AuthApiKeyServiceImpl authService) {
        this.authService = authService;
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateApiKey(@RequestParam String apiKey) {
        boolean isValid = authService.isApiKeyValid(apiKey);
        return ResponseEntity.ok(isValid);
    }
}
