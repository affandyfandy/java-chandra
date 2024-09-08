package com.assignment2.authorization.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@SuppressWarnings("deprecation")
@Component
public class JwtGenerator {

    @Value("${jwt.private.key}")
    private String privateKey;

    @Value("${jwt.public.key}")
    private String publicKey;

    private Key signingKey;
    private Key verificationKey;

    @PostConstruct
    public void init() {
        byte[] privateKeyBytes = java.util.Base64.getDecoder().decode(privateKey);
        byte[] publicKeyBytes = java.util.Base64.getDecoder().decode(publicKey);
        this.signingKey = Keys.hmacShaKeyFor(privateKeyBytes);
        this.verificationKey = Keys.hmacShaKeyFor(publicKeyBytes);
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + 7000000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(verificationKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(verificationKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}