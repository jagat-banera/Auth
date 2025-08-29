package com.app.Auth.Utils;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;


@Component
public class RandomTokenGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateToken(){
        byte[] randomBytes = new byte[32];

        secureRandom.nextBytes(randomBytes);

        return Base64.getEncoder().encodeToString(randomBytes);
    }

}
