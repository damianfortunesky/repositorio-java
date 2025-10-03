package com.usermanager.usermanager.api.dto.request;

public class UserLoginRequestDto {
    private String username;
    private String password;

    public UserLoginRequestDto() {
        // Constructor vacío obligatorio para que Jackson pueda instanciar el DTO
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