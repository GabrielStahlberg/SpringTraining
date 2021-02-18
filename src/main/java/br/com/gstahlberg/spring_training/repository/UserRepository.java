package br.com.gstahlberg.spring_training.repository;

import br.com.gstahlberg.spring_training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
