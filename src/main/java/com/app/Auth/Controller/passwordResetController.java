package com.app.Auth.Controller;

import com.app.Auth.DTOs.passResetDTO;
import com.app.Auth.Repository.UserRepository;
import com.app.Auth.UserEnitiy.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class passwordResetController {

    UserRepository userRepository ;



    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody passResetDTO resetUser){

        return ResponseEntity.ok().body("You will get Reset Link on mail if this Email Exists");

    }


}
