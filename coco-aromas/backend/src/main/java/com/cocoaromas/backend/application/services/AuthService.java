package com.cocoaromas.backend.application.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cocoaromas.backend.api.dto.response.LoginResponseDto;
import com.cocoaromas.backend.api.dto.response.RegisterResponseDto;

import com.cocoaromas.backend.domain.models.Role;
import com.cocoaromas.backend.domain.models.User;
import com.cocoaromas.backend.domain.ports.RoleRepository;
import com.cocoaromas.backend.domain.ports.UserRepository;

import com.cocoaromas.backend.infrastructure.security.JwtProvider;

/*
*   UserService, se encarga de: 
*
*       autenticar usuario (email + password)
*
*       generar token JWT
*
*       validar credenciales
*
*       retornar token + datos básicos
*/

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    // --------------------------------------------------------------
    // LOGIN
    // --------------------------------------------------------------
    public Optional<LoginResponseDto> login(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPasswordHash()))
                .map(user -> {
                    String role = user.getRole() != null ? user.getRole().getName() : "CLIENT";

                    // Genera token JWT
                    String token = jwtProvider.generateToken(user.getId(), role);

                    // Crea respuesta DTO
                    return new LoginResponseDto(
                            token,
                            "Bearer",
                            jwtProvider.getExpirationMs(),
                            user.getUsername(),
                            user.getId(),
                            role);
                });
    }

    // --------------------------------------------------------------
    // REGISTER
    // --------------------------------------------------------------
    public Optional<RegisterResponseDto> register(String email, String username, String rawPassword) {

        // 1. Verificar si el usuario ya existe
        if (userRepository.findByEmail(email).isPresent()) {
            return Optional.empty();
        }

        // 2. Encriptar contraseña
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // 3. Obtener el rol por defecto CLIENT
        Role clientRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        // 4. Crear usuario nuevo
        User newUser = new User(email, username, encodedPassword);
        newUser.setRole(clientRole);

        // 5. Guardar usuario
        User savedUser = userRepository.save(newUser);

        // 7. Crear respuesta DTO
        RegisterResponseDto response = new RegisterResponseDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getUsername(),
                savedUser.getRole().getName(),  // devolver el nombre, no el objeto Role
                savedUser.getIsActive(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt(),
                "User registered successfully"
            );

        return Optional.of(response);
    }
}