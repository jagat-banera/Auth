package com.app.Auth.Controller;

import com.app.Auth.DTOs.SignUpDTO;
import com.app.Auth.Services.SignupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {


    SignupService signupService ;

    public SignupController(SignupService signupService ){
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> SignupRequest (@Valid @RequestBody SignUpDTO user , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Signup Unsuccessful : " + bindingResult.getAllErrors().);
        }
        signupService.register(user);
        return ResponseEntity.ok("User Registered Successfully");
    }

}
