package com.cocoaromas.backend.application.services;

import com.cocoaromas.backend.api.dto.request.UserUpdateRequestDto;
import com.cocoaromas.backend.api.dto.response.UserResponseDto;
import com.cocoaromas.backend.application.mapper.UserMapper;
import com.cocoaromas.backend.domain.models.User;
import com.cocoaromas.backend.domain.ports.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Obtener todos los usuarios
    public List<UserResponseDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    // Buscar usuario por ID
    public Optional<UserResponseDto> findById(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto);
    }

    // Actualizar usuario
    public UserResponseDto update(UUID id, UserUpdateRequestDto dto) {
        return userRepository.findById(id)
                .map(user -> {
                    UserMapper.updateEntity(
                            user,
                            dto,
                            dto.getPassword() != null
                                    ? passwordEncoder.encode(dto.getPassword())
                                    : user.getPasswordHash()
                    );
                    User updated = userRepository.save(user);
                    return UserMapper.toDto(updated);
                })
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    // Eliminar usuario
    public void delete(UUID id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new NoSuchElementException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
