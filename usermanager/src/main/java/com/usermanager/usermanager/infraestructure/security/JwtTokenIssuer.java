package com.usermanager.usermanager.infraestructure.security;

import com.usermanager.usermanager.application.ports.TokenIssuer;
import com.usermanager.usermanager.domain.model.User;
import org.springframework.stereotype.Component;

// JwtTokenIssuer es una implementación concreta del puerto TokenIssuer, que utiliza JwtUtil para generar tokens JWT.

@Component
public class JwtTokenIssuer implements TokenIssuer {

    private final JwtUtil jwt;

    public JwtTokenIssuer(JwtUtil jwt) {
        this.jwt = jwt;
    }

    @Override
    public String issueFor(User user) {
        return jwt.generateToken(user.getId(), user.getUsername(), user.getEmail());
    }
}