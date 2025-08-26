package com.app.Auth.Controller;


import com.app.Auth.DTOs.LoginDTO;
import com.app.Auth.Services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    LoginService loginService ;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login-user")
    public String setHello(@Valid @RequestBody LoginDTO loginDTO , BindingResult bindingResult) {


        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors().toString();
        }

        return loginService.loginUser(loginDTO);
    }

}
