package com.cocoaromas.backend.domain.ports;

import java.util.*;

import com.cocoaromas.backend.domain.models.User;

public interface UserRepository {
    User save(User user);

    List<User> findAll();

    void deleteById(UUID id);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}