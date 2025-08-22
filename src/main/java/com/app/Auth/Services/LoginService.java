package com.app.Auth.Services;

import com.app.Auth.Repository.UserRepository;
import com.app.Auth.UserEnitiy.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository ;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found : " + username));

    }
}
