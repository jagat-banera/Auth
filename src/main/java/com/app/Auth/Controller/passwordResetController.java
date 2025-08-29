package com.app.Auth.Controller;

import com.app.Auth.DTOs.passResetDTO;
import com.app.Auth.Repository.UserRepository;
import com.app.Auth.Services.passwordResetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class passwordResetController {


    private final UserRepository userRepository ;

    private final passwordResetService resetService ;

    public passwordResetController(UserRepository userRepository, passwordResetService resetService) {
        this.userRepository = userRepository;
        this.resetService = resetService;
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody passResetDTO resetUser){

        // Call the password reset service

        resetService.ResetPassword(resetUser);


        return ResponseEntity.ok().body("You will get Reset Link on mail if this Email Exists");

    }





}
