package com.cocoaromas.backend.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 1 - Leer el encabezado Authorization
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            // 2 - Validar token
            if (jwtProvider.validateToken(token)) {
                // 3 - Extraer datos del token
                UUID userId = jwtProvider.getUserIdFromToken(token);
                String role = jwtProvider.getRoleFromToken(token);

                // 4 - Crear autenticación temporal
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userId, // principal
                        null, // credentials
                        Collections.singleton(() -> "ROLE_" + role) // autoridad
                );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                // 5- Registrar autenticación en el contexto de Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 6 - Continuar con el resto de la cadena de filtros
        filterChain.doFilter(request, response);
    }
}