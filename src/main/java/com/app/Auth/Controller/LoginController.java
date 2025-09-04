package com.app.Auth.Controller;


import com.app.Auth.DTOs.LoginDTO;
import com.app.Auth.DTOs.authResult;
import com.app.Auth.Services.LoginService;
import com.app.Auth.Services.jwtService;
import com.app.Auth.Utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public ResponseEntity<ApiResponse<Map<String,String>>> setHello(@Valid @RequestBody LoginDTO loginDTO , BindingResult bindingResult) {

        // Check for Validation Errors
        if(bindingResult.hasErrors()){
            List loginError = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();

            return ResponseEntity.badRequest().body(new ApiResponse<Map<String,String>>("error" , loginError , null));
        }

        // Check for DB Errors while Inserting
        authResult result = loginService.loginUser(loginDTO);
        boolean loginStatus = result.getStatus();

        //
        if(!loginStatus){
            return ResponseEntity.badRequest().body(new ApiResponse<Map<String,String>>("error" , List.of(result.getMessage()) , null ) );
        }

        return ResponseEntity.ok()
                .body(new ApiResponse<Map<String,String>>
                        ("success" ,
                                List.of(result.getMessage()) ,
                                    Map.of("access_token" , jwt.generateAccessToken(loginDTO.getUsername()) ,
                                            "refresh_token" , jwt.generateRefreshToken(loginDTO.getUsername()) )
                        )
                );
    }

}
