package com.javaprojects.jwt_login.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
})
public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, unique = true, length = 50)
        private String username;

        @Column(nullable = false, unique = true, length = 100)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column(length = 50)
        private String firstname;

        @Column(length = 50)
        private String lastname;

        @Column(length = 50)
        private String country;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role;

        // =======================
        // Constructores
        // =======================
        public User() {
        }

        public User(Integer id, String username, String email, String password,
                        String firstname, String lastname, String country, Role role) {
                this.id = id;
                this.username = username;
                this.email = email;
                this.password = password;
                this.firstname = firstname;
                this.lastname = lastname;
                this.country = country;
                this.role = role;
        }

        // =======================
        // Getters y Setters
        // =======================
        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        @Override
        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @Override
        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getFirstname() {
                return firstname;
        }

        public void setFirstname(String firstname) {
                this.firstname = firstname;
        }

        public String getLastname() {
                return lastname;
        }

        public void setLastname(String lastname) {
                this.lastname = lastname;
        }

        public String getCountry() {
                return country;
        }

        public void setCountry(String country) {
                this.country = country;
        }

        public Role getRole() {
                return role;
        }

        public void setRole(Role role) {
                this.role = role;
        }

        // =======================
        // Métodos de UserDetails
        // =======================
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority(role.name()));
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}
