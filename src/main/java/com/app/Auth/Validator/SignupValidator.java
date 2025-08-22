package com.app.Auth.Validator;

import com.app.Auth.DTOs.SignUpDTO;
import com.app.Auth.Repository.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class SignupValidator implements Validator {

    UserRepository userRepository ;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpDTO user = (SignUpDTO) target ;


        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "username.alreadyExists" , "Username Already Exists");
        }


    }

}
