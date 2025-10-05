package com.cocoaromas.backend.application.mapper;

import com.cocoaromas.backend.api.dto.request.RegisterRequestDto;
import com.cocoaromas.backend.api.dto.request.UserUpdateRequestDto;
import com.cocoaromas.backend.api.dto.response.UserResponseDto;
import com.cocoaromas.backend.domain.models.User;

public class UserMapper {

    // Constructor privado para evitar instanciación (regla SonarQube)
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    // Convierte User (domain) → UserResponseDto (API)
    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getRole() != null ? user.getRole().getName() : "USER",
                user.getIsActive(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    // Convierte RegisterRequestDto (API) → User (domain)
    public static User toEntity(RegisterRequestDto dto, String encodedPassword) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPasswordHash(encodedPassword);
        return user;
    }

    // Actualiza entidad existente a partir de DTO de actualización
    public static void updateEntity(User user, UserUpdateRequestDto dto, String encodedPassword) {
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            user.setPasswordHash(encodedPassword);
        }
        if (dto.getIsActive() != null) {
            user.setIsActive(dto.getIsActive());
        }
    }
}
