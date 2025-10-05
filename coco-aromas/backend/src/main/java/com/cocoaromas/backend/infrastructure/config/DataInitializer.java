package com.cocoaromas.backend.infrastructure.config;

import com.cocoaromas.backend.domain.models.Role;
import com.cocoaromas.backend.domain.ports.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {

        // Lista de roles por defecto
        List<String> defaultRoles = List.of("USER", "CLIENT", "BOSS", "ADMIN");

        for (String roleName : defaultRoles) {
            boolean exists = roleRepository.findByName(roleName).isPresent();

            if (!exists) {
                Role newRole = new Role();
                newRole.setName(roleName);
                roleRepository.save(newRole);
                System.out.println("Role created: " + roleName);
            } else {
                System.out.println("Role already exists: " + roleName);
            }
        }
    }
}