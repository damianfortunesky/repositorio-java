package com.usermanager.usermanager.api.dto.request;

public class UserRegisterRequestDto {
    private String email;
    private String username;
    private String password;

    public UserRegisterRequestDto() {
        // Constructor vacío obligatorio para que Jackson pueda instanciar el DTO
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
