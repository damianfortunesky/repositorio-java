package com.cocoaromas.backend.domain.ports;

import com.cocoaromas.backend.domain.models.UserProfile;
import java.util.*;

public interface UserProfileRepository {

    // Buscar perfil por ID de perfil
    Optional<UserProfile> findById(Long id);

    // Buscar perfil por ID de usuario
    Optional<UserProfile> findByUserId(UUID userId);

    // Obtener todos los perfiles
    List<UserProfile> findAll();

    // Guardar o actualizar perfil
    UserProfile save(UserProfile profile);

    // Eliminar perfil por entidad
    void delete(UserProfile profile);
}
