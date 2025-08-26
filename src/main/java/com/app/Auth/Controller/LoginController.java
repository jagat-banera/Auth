package com.app.Auth.Controller;


import com.app.Auth.DTOs.LoginDTO;
import com.app.Auth.DTOs.authResult;
import com.app.Auth.Services.LoginService;
import com.app.Auth.Services.jwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {


    LoginService loginService ;
    jwtService jwt ;
    public LoginController(LoginService loginService , jwtService jwt ){
        this.loginService = loginService;
        this.jwt = jwt ;
    }

    @PostMapping("/login-user")
    public ResponseEntity<?> setHello(@Valid @RequestBody LoginDTO loginDTO , BindingResult bindingResult) {


        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(Map.of("error" , "username or password is empty"));
        }


        authResult result = loginService.loginUser(loginDTO);
        boolean loginStatus = result.getStatus();

        if(!loginStatus){
            return ResponseEntity.badRequest().body(Map.of("error" , result.getMessage()));
        }

        return ResponseEntity.ok().body(Map.of("success" , result.getMessage() , "token" , jwt.generateToken(loginDTO.getUsername()) ));
    }

}
