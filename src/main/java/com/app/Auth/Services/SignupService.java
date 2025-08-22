package com.app.Auth.Services;

import com.app.Auth.DTOs.SignUpDTO;
import com.app.Auth.Repository.UserRepository;
import com.app.Auth.UserEnitiy.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {



    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder ;



    public SignupService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository ;
        this.passwordEncoder = passwordEncoder;

    }


    // Validating and Saving the User
    public void register(SignUpDTO DTOuser){

        //Encoding the password before saving

        String encodedPassword = passwordEncoder.encode(DTOuser.getPassword());

        // Convert the DTO object to User
        User user = new User(DTOuser.getUsername() ,encodedPassword);

        userRepository.save(user);

    }







}
