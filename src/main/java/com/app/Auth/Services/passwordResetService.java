package com.app.Auth.Services;


import com.app.Auth.DTOs.passResetDTO;
import com.app.Auth.Repository.PasswordResetRepo;
import com.app.Auth.Repository.UserRepository;
import com.app.Auth.UserEnitiy.User;
import com.app.Auth.UserEnitiy.passwordReset;
import com.app.Auth.Utils.RandomTokenGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class passwordResetService {

    private final PasswordResetRepo passwordResetRepo ;
    private final UserRepository userRepository ;


    public passwordResetService(PasswordResetRepo passwordResetRepo, UserRepository userRepository, RandomTokenGenerator randomTokenGenerator) {
        this.passwordResetRepo = passwordResetRepo;
        this.userRepository = userRepository;
    }


    public void ResetPassword(passResetDTO resetUser){

        //Check if user exists
        Optional<User> user = userRepository.findByEmail(resetUser.getEmail());

        if(user.isPresent()){

            String token = RandomTokenGenerator.generateToken();

            // Create a Password Reset Entity to store in the database

            passwordReset userData = new passwordReset(user.get().getId() , token);

            // Save the data in Database (Table Name = Password_Reset_Token)

            passwordResetRepo.save(userData);

        }

    }




}
