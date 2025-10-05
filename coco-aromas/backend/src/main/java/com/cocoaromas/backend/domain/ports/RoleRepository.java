package com.cocoaromas.backend.domain.ports;

import java.util.Optional;
import com.cocoaromas.backend.domain.models.Role;

public interface RoleRepository {

    Optional<Role> findByName(String name);

    Role save(Role role);
}
