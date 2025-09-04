package com.app.Auth.Services;

import com.app.Auth.Utils.RandomTokenGenerator;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.util.*;


@Service
public class jwtService {

    @Value("${jwt.secret}")
    private String secret ;

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(String username){
        Map<String, Objects> claim = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claim)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .and()
                .signWith(getKey())
                .compact();

    }

    public String generateRefreshToken(String username){
        Map<String,Objects> claim = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claim)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Duration.ofDays(7).toMillis()))
                .and()
                .signWith(getKey())
                .compact();
    }

    public boolean validateToken(String token){

        // Check if the key is valid for not
        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);

            return true ;

        }
        catch (JwtException e){
            return false ;
        }
    }

    public String getUsername(String token){

        try{

            return
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }
        catch (JwtException e) {
            return null;
        }
    }




}
