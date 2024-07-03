package org.assignment1.assignment3.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestService {
    private String message;

    public RequestService() {
        this.message = "Request Service: " + System.currentTimeMillis();
    }
}
