package com.app.Auth.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class RefreshTokenDTO {

    @NotEmpty(message = "Token Filed is Empty")
    private String token ;

    public String getToken() {
        return token;
    }

}
