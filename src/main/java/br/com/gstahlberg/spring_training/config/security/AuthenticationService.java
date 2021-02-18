package br.com.gstahlberg.spring_training.config.security;

import br.com.gstahlberg.spring_training.model.User;
import br.com.gstahlberg.spring_training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
            User simulated:
            email: gabriel@gmail.com
            pass: 123456 (encrypted: $2a$10$PRe3vlUluGGty2yuIgM6duNGXPPnV7cAUy0Tyrxc08812Bpt2RV72)
         */
        Optional<User> user = userService.findUserByEmail(username);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Invalid credentials!");
    }
}
