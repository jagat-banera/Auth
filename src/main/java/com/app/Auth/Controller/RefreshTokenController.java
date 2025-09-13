package com.app.Auth.Controller;

import com.app.Auth.DTOs.RefreshTokenDTO;
import com.app.Auth.Services.jwtService;
import com.app.Auth.Utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RefreshTokenController {

    private final jwtService jwt ;


    public RefreshTokenController(jwtService jwt) {
        this.jwt = jwt;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<Map<String,String>>> refresh(@Valid @RequestBody RefreshTokenDTO refreshToken){

        //Check if the Refresh token is valid or not

        if(jwt.validateToken(refreshToken.getToken())){

            // Token is valid then get the subject (username)
            String username = jwt.getUsername(refreshToken.getToken());

            // Create a new Access Token from the username
            String accessToken = jwt.generateAccessToken(username);

            // Return the generated Access Token
            return ResponseEntity
                    .ok()
                    .body(
                            new ApiResponse<Map<String,String>>("success" , List.of("Token Succesfully Generated") , Map.of("access_token" , accessToken))
                    );

        }

        // Token Validation Failed Return

        return ResponseEntity
                .badRequest()
                .body(
                        new ApiResponse<>("error" , List.of("Invalid Refresh Token") , null)
                );

    }

}
