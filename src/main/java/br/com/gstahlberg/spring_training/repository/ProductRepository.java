package br.com.gstahlberg.spring_training.repository;

import br.com.gstahlberg.spring_training.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
