package com.assignment2.resource.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwtGenerator {

    private final SecretKey publicKey;

    public JwtGenerator(@Value("${jwt.public.key}") String publicKeyBase64) {
        if (publicKeyBase64 == null || publicKeyBase64.isEmpty()) {
            throw new IllegalArgumentException("Public key must be provided");
        }
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
        this.publicKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}