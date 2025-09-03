package com.app.Auth.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class ResetPasswordDTO {

    @NotEmpty(message = "Input is Empty")
    private String token ;

    public String getToken() {
        return token;
    }

}
