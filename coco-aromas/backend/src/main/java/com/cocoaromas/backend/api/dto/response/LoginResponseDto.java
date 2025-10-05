package com.cocoaromas.backend.api.dto.response;

import java.util.UUID;

public class LoginResponseDto {
    private String token;
    private String tokenType;
    private Long expiresIn;
    private String username;
    private UUID userId;
    private String role;

    public LoginResponseDto(String token, String tokenType, Long expiresIn, String username, UUID userId, String role) {
        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.username = username;
        this.userId = userId;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}