package br.com.gstahlberg.spring_training.service;

import br.com.gstahlberg.spring_training.model.User;
import br.com.gstahlberg.spring_training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
