package com.app.Auth.Controller;

import com.app.Auth.DTOs.ForgotPasswordDTO;
import com.app.Auth.DTOs.ResetPasswordDTO;
import com.app.Auth.Services.PasswordResetService;
import com.app.Auth.Utils.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class passwordResetController {




    private final PasswordResetService passwordResetService ;

    public passwordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@Valid @RequestBody ForgotPasswordDTO resetUser){

        // Call the Password Reset service

        passwordResetService.forgotPassword(resetUser);


        // Response to the API
        return ResponseEntity.ok().body(new ApiResponse<String>("success" , List.of("You will get Reset Link on mail if this Email Exists") , null));

    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO){

        // Call the Reset Service

        if(passwordResetService.isValidResetToken(resetPasswordDTO.getToken())){
            return ResponseEntity.ok().body(new ApiResponse<>("success" , List.of("Valid Token Proceed to Change Password") , null));
        }

        return ResponseEntity.badRequest().body(new ApiResponse<>("error" , List.of("Invalid Token") , null));
    }






}
