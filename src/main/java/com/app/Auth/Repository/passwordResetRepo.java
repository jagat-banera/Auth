package com.app.Auth.Repository;

import com.app.Auth.UserEnitiy.User;
import com.app.Auth.UserEnitiy.passwordReset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface passwordResetRepo extends JpaRepository<passwordReset,Long> {

}
