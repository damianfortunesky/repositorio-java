package com.cocoaromas.backend.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * En esta clase se definetoda la configuración de seguridad de Spring Boot
 * 
 * Qué rutas están protegidas y cuáles son públicas
 * 
 * Cómo se maneja la autenticación
 * 
 * Qué bean usar para el PasswordEncoder
 * 
 * Cómo se valida el JWT en cada request
 * 
 */

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configuración principal de seguridad
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                
                .csrf(csrf -> csrf.disable())                                                                   // Desactiva CSRF porque usamos JWT                
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   // Política de sesión sin estado (stateless)               
                .authorizeHttpRequests(auth -> auth                                                             // Autorizaciones de endpoints
                        .requestMatchers("/","/api/v1/auth/**").permitAll()  
                        .requestMatchers("/api/v1/users/**").authenticated()                                    // Rutas públicas
                        .requestMatchers("/error").permitAll()                                                  // Evita errores al iniciar
                        .anyRequest().authenticated()                                                           // Resto rutas requieren JWT
                )                
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);          // Agrega filtro JWT antes del de autenticación

        return http.build();
    }

    /**
     * Bean para encriptar contraseñas (BCrypt)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Manager de autenticación (usado por Spring internamente)
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}