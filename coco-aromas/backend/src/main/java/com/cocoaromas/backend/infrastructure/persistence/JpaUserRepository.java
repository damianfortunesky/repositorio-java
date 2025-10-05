package com.cocoaromas.backend.infrastructure.persistence;

import com.cocoaromas.backend.domain.models.User;
import com.cocoaromas.backend.domain.ports.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID>, UserRepository {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
