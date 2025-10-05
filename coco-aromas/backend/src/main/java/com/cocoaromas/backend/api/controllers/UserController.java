package com.cocoaromas.backend.api.controllers;

import com.cocoaromas.backend.api.dto.request.UserUpdateRequestDto;
import com.cocoaromas.backend.api.dto.response.UserResponseDto;
import com.cocoaromas.backend.application.services.UserService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Listar todos los usuarios
    @GetMapping
    @PreAuthorize("hasRole('EMPLOYEE','BOSS','ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Buscar usuario por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','BOSS','ADMIN')")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('BOSS','ADMIN')")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable UUID id,
            @RequestBody UserUpdateRequestDto dto
    ) {
        UserResponseDto updated = userService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    //Eliminar usuario
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('BOSS','ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
