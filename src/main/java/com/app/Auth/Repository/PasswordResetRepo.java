package com.app.Auth.Repository;

import com.app.Auth.UserEnitiy.passwordReset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRepo extends JpaRepository<passwordReset,Long> {

    Optional<passwordReset> findByToken(String token);

}
