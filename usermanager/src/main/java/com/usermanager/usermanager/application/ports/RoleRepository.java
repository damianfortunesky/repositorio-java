package com.usermanager.usermanager.application.ports;

import com.usermanager.usermanager.domain.model.Role;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}
