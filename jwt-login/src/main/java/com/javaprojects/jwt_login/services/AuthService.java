package com.javaprojects.jwt_login.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javaprojects.jwt_login.models.Role;
import com.javaprojects.jwt_login.models.User;
import com.javaprojects.jwt_login.repositories.UserRepository;
import com.javaprojects.jwt_login.security.AuthResponse;
import com.javaprojects.jwt_login.security.LoginRequest;
import com.javaprojects.jwt_login.security.RegisterRequest;

@Service
public class AuthService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;

        public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
                this.authenticationManager = authenticationManager;
                this.jwtService = jwtService;
        }

        public AuthResponse login(LoginRequest request) {
                // 1. Validar credenciales con Spring Security
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));

                // 2. Obtener usuario desde DB
                User user = userRepository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // 3. Generar token
                String token = jwtService.getToken(user);

                // 4. Retornar token
                return new AuthResponse(token);
        }

        public AuthResponse register(RegisterRequest request) {
                // 1. Crear usuario
                User user = new User();
                user.setUsername(request.getUsername());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setEmail(request.getEmail());
                user.setFirstname(request.getFirstname());
                user.setLastname(request.getLastname());
                user.setCountry(request.getCountry());
                user.setRole(Role.USER);

                // 2. Guardar usuario en DB
                userRepository.save(user);

                // 3. Generar token
                String token = jwtService.getToken(user);

                // 4. Retornar token
                return new AuthResponse(token);
        }
}
