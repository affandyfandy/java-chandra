package org.assignment1.authentication.service.impl;

import org.assignment1.authentication.repository.ApiKeyRepository;
import org.assignment1.authentication.service.AuthApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthApiKeyServiceImpl implements AuthApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    @Autowired
    public AuthApiKeyServiceImpl(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public boolean isApiKeyValid(String apiKey) {
        return apiKeyRepository.findByKey(apiKey).isPresent();
    }
}
