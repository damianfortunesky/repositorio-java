package com.cocoaromas.backend.api.controllers;

import com.cocoaromas.backend.api.dto.request.UserProfileRequestDto;
import com.cocoaromas.backend.api.dto.response.UserProfileResponseDto;

import com.cocoaromas.backend.application.services.UserProfileService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    // ===========================================================
    // 1 - Crear o actualizar perfil (self o admin/empleado)
    // ===========================================================
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or #userId == authentication.principal.id")
    @PutMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponseDto> createOrUpdateProfile(
            @PathVariable UUID userId,
            @RequestBody UserProfileRequestDto request) {
        UserProfileResponseDto response = userProfileService.create(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // ===========================================================
    // 2 - Obtener perfil por ID de usuario
    // ===========================================================
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or #userId == authentication.principal.id")
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponseDto> getProfileByUserId(@PathVariable UUID userId) {
        UserProfileResponseDto response = userProfileService.getByUserId(userId);
        return ResponseEntity.ok(response);
    }

    // ===========================================================
    // 3 - Actualizar campos específicos opcionales (PATCH)
    // ===========================================================
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or #userId == authentication.principal.id")
    @PatchMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponseDto> patchProfile(
            @PathVariable UUID userId,
            @RequestBody UserProfileRequestDto request) {
        UserProfileResponseDto response = userProfileService.create(userId, request);
        return ResponseEntity.ok(response);
    }

}
