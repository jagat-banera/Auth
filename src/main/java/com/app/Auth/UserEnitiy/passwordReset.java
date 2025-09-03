package com.app.Auth.UserEnitiy;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
public class passwordReset{
    @Id
    private long user_id;

    private String token ;

    private LocalDateTime expires_at = LocalDateTime.now().plusMinutes(3);

    private boolean used  = false ;

    private LocalDateTime created_at = LocalDateTime.now();


    //Default
    public passwordReset() {
    }

    // Constructors
    public passwordReset(long user_id, String token) {
        this.user_id = user_id;
        this.token = token;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpires_at() {
        return expires_at;
    }

    public boolean isUsed() {
        return used;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }


    public void setUsed(boolean used) {
        this.used = used;
    }
}
