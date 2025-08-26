package com.app.Auth.Services;

import com.app.Auth.DTOs.LoginDTO;
import com.app.Auth.DTOs.authResult;
import com.app.Auth.Repository.UserRepository;
import com.app.Auth.UserEnitiy.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService  {

    private final UserRepository userRepository ;


    private final PasswordEncoder passwordEncoder ;
    public LoginService(UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public authResult loginUser(LoginDTO loginDTO){
        authResult result  = new authResult();
        result.setStatus(false);


        if(userRepository.findByUsername(loginDTO.getUsername()).isPresent()){

            String password = userRepository.findByUsername(loginDTO.getUsername()).get().getPassword();

            if(passwordEncoder.matches(loginDTO.getPassword() , password)){
                result.setStatus(true);
                result.setMessage("Successfully Logged In");
            }
            else {
                result.setMessage("Wrong Password");
            }


        }

        else{
            result.setMessage("User Not found");
        }

        return result;
    }



}
