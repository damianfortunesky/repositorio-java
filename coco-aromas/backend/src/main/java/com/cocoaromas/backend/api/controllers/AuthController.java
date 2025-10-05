package com.cocoaromas.backend.api.controllers;

import com.cocoaromas.backend.api.dto.request.LoginRequestDto;
import com.cocoaromas.backend.api.dto.request.RegisterRequestDto;
import com.cocoaromas.backend.api.dto.response.LoginResponseDto;
import com.cocoaromas.backend.api.dto.response.RegisterResponseDto;
import com.cocoaromas.backend.application.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ===========================================================
    // LOGIN
    // ===========================================================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {

        Optional<LoginResponseDto> response = authService.login(request.getEmail(), request.getPassword());

        // Usa tipo explícito para ResponseEntity<LoginResponseDto>
        return response.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid credentials"));
    }

    // ===========================================================
    // REGISTER
    // ===========================================================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto request) {

        Optional<RegisterResponseDto> response = authService.register(
                request.getEmail(),
                request.getUsername(),
                request.getPassword());

        return response.<ResponseEntity<?>>map(res -> ResponseEntity.status(HttpStatus.CREATED).body(res))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("User already exists"));
    }
}
