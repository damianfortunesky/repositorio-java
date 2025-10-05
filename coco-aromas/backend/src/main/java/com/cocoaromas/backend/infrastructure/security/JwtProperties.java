package com.cocoaromas.backend.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMs;

    @Value("${jwt.issuer}")
    private String issuer;

    public String getSecret() {
        return secret;
    }

    public long getExpirationMs() {
        return expirationMs;
    }

    public String getIssuer() {
        return issuer;
    }
}
