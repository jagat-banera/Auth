package com.app.Auth.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SignUpDTO {


    @NotEmpty(message = "Username is required")
    private String username ;

    @NotEmpty(message = "Password is required")
    @Size(min = 6 , message = "Password must be atleast 6 characters")
    private String password ;


    private String role = "ROLE_ADMIN";

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
