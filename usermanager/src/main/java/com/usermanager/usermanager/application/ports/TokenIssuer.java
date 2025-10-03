package com.usermanager.usermanager.application.ports;

import com.usermanager.usermanager.domain.model.User;

// Puertos: Son interfaces que declaran lo que la aplicación necesita del exterior.
// TokenIssuer es una interfaz (puerto) definida en la capa de application, que expone una operación de “emitir token para un usuario”.

public interface TokenIssuer {
    String issueFor(User user);
}