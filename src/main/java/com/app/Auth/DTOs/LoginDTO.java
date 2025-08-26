package com.app.Auth.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {

    @NotEmpty(message = "Username is Required")
    private String username ;

    @NotEmpty(message = "Password is required")
    private String password ;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
