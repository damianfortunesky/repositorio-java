package com.usermanager.usermanager.application.service;

import com.usermanager.usermanager.application.ports.RoleRepository;
import com.usermanager.usermanager.application.ports.UserRepository;
import com.usermanager.usermanager.application.ports.TokenIssuer;
import com.usermanager.usermanager.domain.model.Role;
import com.usermanager.usermanager.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenIssuer tokenIssuer;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            TokenIssuer tokenIssuer,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenIssuer = tokenIssuer;
        this.passwordEncoder = passwordEncoder;
    }

    // Registro de nuevo usuario + emisión de token
    public String register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Encriptar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Asignar rol CLIENT por defecto
        Role clientRole = roleRepository.findByName("CLIENT").orElseThrow(() -> new IllegalStateException("Default role CLIENT not found"));   
        Set<Role> roles = new HashSet<>();
        roles.add(clientRole);
        user.setRoles(roles);

        // Persistir y emitir token
        User savedUser = userRepository.save(user);
        return tokenIssuer.issueFor(savedUser);
    }

    // Login + emisión de token
    public String login(String username, String rawPassword) {
        // Buscar usuario por username o email
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User Invalid"));

        // Validar contraseña
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) { throw new IllegalArgumentException("Wrong password");
        }

        // Emitir token
        return tokenIssuer.issueFor(user);
    }
}
