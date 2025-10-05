package com.cocoaromas.backend.application.services;

import com.cocoaromas.backend.api.dto.request.UserProfileRequestDto;
import com.cocoaromas.backend.api.dto.response.UserProfileResponseDto;
import com.cocoaromas.backend.application.mapper.UserProfileMapper;
import com.cocoaromas.backend.domain.models.User;
import com.cocoaromas.backend.domain.models.UserProfile;
import com.cocoaromas.backend.domain.ports.UserRepository;
import com.cocoaromas.backend.domain.ports.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    // ===========================================================
    // 1 - Crear o actualizar el perfil de usuario
    // ===========================================================
    public UserProfileResponseDto create(UUID userId, UserProfileRequestDto dto) {

        // Buscar el usuario asociado
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        // Buscar si el perfil ya existe
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElse(new UserProfile());

        // Asociar el usuario al perfil
        profile.setUser(user);

        // Actualizar los campos (solo los no nulos)
        UserProfileMapper.updateEntity(profile, dto);

        // Guardar
        UserProfile saved = userProfileRepository.save(profile);

        return UserProfileMapper.toDto(saved);
    }

    // ===========================================================
    // 2 - Obtener perfil por ID de usuario
    // ===========================================================
    public UserProfileResponseDto getByUserId(UUID userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("Profile not found for user ID: " + userId));

        return UserProfileMapper.toDto(profile);
    }
}
