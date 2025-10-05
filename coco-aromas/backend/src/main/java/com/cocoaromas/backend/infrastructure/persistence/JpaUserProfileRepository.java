package com.cocoaromas.backend.infrastructure.persistence;

import com.cocoaromas.backend.domain.models.UserProfile;
import com.cocoaromas.backend.domain.ports.UserProfileRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserProfileRepository extends JpaRepository<UserProfile, Long>, UserProfileRepository {

    // Permite buscar el perfil asociado a un usuario
    Optional<UserProfile> findByUserId(UUID userId);

    // Verifica si un usuario ya tiene perfil
    boolean existsByUserId(UUID userId);
}
