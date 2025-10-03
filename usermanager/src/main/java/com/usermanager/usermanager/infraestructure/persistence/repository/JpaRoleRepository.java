package com.usermanager.usermanager.infraestructure.persistence.repository;

import com.usermanager.usermanager.domain.model.Role;
import com.usermanager.usermanager.application.ports.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adaptador de infraestructura que conecta la interfaz de dominio (RoleRepository) con la implementación concreta de Spring Data JPA.
 *
 * - RoleRepository: contrato de dominio -> define las operaciones permitidas sobre Role.
 * - JpaRepository: interfaz de Spring que provee CRUD genérico.
 * - JpaRoleRepository: une ambas, Spring genera el bean real en runtime.
 *
 * De esta manera en la capa de aplicación inyectamos RoleRepository (la abstracción).
 * Spring nos da esta implementación concreta -> logramos desacoplar el dominio de la infraestructura.
 * 
 */

@Repository
public interface JpaRoleRepository extends JpaRepository<Role, Long>, RoleRepository {
    Optional<Role> findByName(String name);
}
