package org.assignment2.authentication.controller;

import org.assignment2.authentication.service.impl.AuthApiKeyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
