package com.app.Auth.Services;


import com.app.Auth.DTOs.ForgotPasswordDTO;
import com.app.Auth.Repository.PasswordResetRepo;
import com.app.Auth.Repository.UserRepository;
import com.app.Auth.UserEnitiy.User;
import com.app.Auth.UserEnitiy.passwordReset;
import com.app.Auth.Utils.RandomTokenGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordResetService {

    private final PasswordResetRepo passwordResetRepo ;
    private final UserRepository userRepository ;


    public PasswordResetService(PasswordResetRepo passwordResetRepo, UserRepository userRepository, RandomTokenGenerator randomTokenGenerator) {
        this.passwordResetRepo = passwordResetRepo;
        this.userRepository = userRepository;
    }


    public void forgotPassword(ForgotPasswordDTO resetUser){

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


    public boolean isValidResetToken(String token){

        Optional<passwordReset> resetUser = passwordResetRepo.findByToken(token);

        if(resetUser.isPresent()){
            if(resetUser.get().isUsed()){
                // Token Already Used
                return false ;
            }

            if(resetUser.get().getExpires_at().isBefore(LocalDateTime.now())){
                // Token has already expired
                return  false ;
            }

            // A Valid Token is found
            // Change the isUsed to true and save in DB
            passwordReset saveUser = resetUser.get(); // Optional --> Entity
            saveUser.setUsed(true);
            passwordResetRepo.save(saveUser);
            return true ;
        }

        // Token not found
        return false ;

    }








}
