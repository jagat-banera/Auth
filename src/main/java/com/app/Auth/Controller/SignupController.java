package com.app.Auth.Controller;

import com.app.Auth.DTOs.SignUpDTO;
import com.app.Auth.Services.SignupService;
import com.app.Auth.Utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SignupController {


    SignupService signupService ;

    public SignupController(SignupService signupService ){
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> SignupRequest (@Valid @RequestBody SignUpDTO user , BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            // List of all the validation Errors
            List currentErrors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();

            // Response for invalid registration
            return ResponseEntity.badRequest().body(new ApiResponse<>("error" , currentErrors , null));
        }

        // Save User in DB
        signupService.register(user);

        //Response for Valid Registration
        return ResponseEntity.ok(new ApiResponse<>("success", List.of("User Registered Successfully"), null));
    }
}
