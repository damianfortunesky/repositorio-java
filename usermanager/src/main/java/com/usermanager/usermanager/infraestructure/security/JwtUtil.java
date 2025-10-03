package com.usermanager.usermanager.infraestructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

// La encargada de generar tokens JWT firmados

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationSeconds;
    private final String issuer;

    // Constructor con @Value: Spring inyecta automáticamente los valores definidos en tu application.properties
    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration-seconds}") long expirationSeconds, @Value("${jwt.issuer:usermanager}") String issuer) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationSeconds = expirationSeconds;
        this.issuer = issuer;
    }

    // Genera un token JWT con los detalles del usuario
    public String generateToken(UUID userId, String username, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(userId.toString())                                      // Identificador del usuario
                .setIssuer(issuer)                                                  // Quién emitió el token
                .setIssuedAt(Date.from(now))                                        // Fecha de emisión
                .setExpiration(Date.from(now.plusSeconds(expirationSeconds)))       // Vencimiento
                .claim("username", username)                                        // Se agregan claims personalizados
                .claim("email", email)
                .signWith(key, SignatureAlgorithm.HS256)                            // Se firma con HMAC SHA256
                .compact();                                                         // Genera el token final en formato String
    }

}