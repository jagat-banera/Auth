package com.app.Auth.Controller;

import com.app.Auth.DTOs.ForgotPasswordDTO;
import com.app.Auth.DTOs.ResetPasswordDTO;
import com.app.Auth.Services.PasswordResetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class passwordResetController {




    private final PasswordResetService passwordResetService ;

    public passwordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordDTO resetUser){

        // Call the Password Reset service

        passwordResetService.forgotPassword(resetUser);



        return ResponseEntity.ok().body("You will get Reset Link on mail if this Email Exists");

    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO){

        // Call the Reset Service

        if(passwordResetService.isValidResetToken(resetPasswordDTO.getToken())){
            return ResponseEntity.ok().body("Valid Token Proceed to Change Password");
        }

        return ResponseEntity.badRequest().body("Invalid Token");
    }






}
