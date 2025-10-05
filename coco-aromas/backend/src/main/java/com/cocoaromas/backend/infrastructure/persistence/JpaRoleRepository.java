package com.cocoaromas.backend.infrastructure.persistence;

import com.cocoaromas.backend.domain.models.Role;
import com.cocoaromas.backend.domain.ports.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<Role, Long>, RoleRepository {
    Optional<Role> findByName(String name);
}
