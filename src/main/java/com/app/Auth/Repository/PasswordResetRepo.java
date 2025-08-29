package com.app.Auth.Repository;

import com.app.Auth.UserEnitiy.passwordReset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepo extends JpaRepository<passwordReset,Long> {

}
