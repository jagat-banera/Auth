package com.app.Auth.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class passResetDTO {


    @NotEmpty(message = "Email is Required")
    @Email
    private String email ;


    public String getEmail() {
        return email;
    }

}
