package com.usermanager.usermanager.api.controller;

import com.usermanager.usermanager.api.dto.request.UserLoginRequestDto;
import com.usermanager.usermanager.api.dto.request.UserRegisterRequestDto;
import com.usermanager.usermanager.api.dto.response.AuthResponseDto;
import com.usermanager.usermanager.application.service.AuthService;
import com.usermanager.usermanager.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserRegisterRequestDto req) {
        
        // Construcción del modelo de dominio User
        User user = new User();
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());

        // Registrar + emitir token
        String token = authService.register(user);

        // Armar la respuesta
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setType("Bearer");
        response.setExpiresIn(3600L);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginRequestDto req) {
        
        // Validar credenciales + emitir token
        String token = authService.login(req.getUsername(), req.getPassword());

        // Armar la respuesta
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setType("Bearer");
        response.setExpiresIn(3600L);

        return ResponseEntity.ok(response);
    }
}
