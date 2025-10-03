package com.javaprojects.jwt_login.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaprojects.jwt_login.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
