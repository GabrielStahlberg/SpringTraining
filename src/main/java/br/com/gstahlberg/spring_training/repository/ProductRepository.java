package br.com.gstahlberg.spring_training.repository;

import br.com.gstahlberg.spring_training.model.Product;
import br.com.gstahlberg.spring_training.model.enumeration.EnumCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findProductByCategory(Pageable pagination, EnumCategory category);
}
