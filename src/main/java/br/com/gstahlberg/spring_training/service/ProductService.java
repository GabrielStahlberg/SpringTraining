package br.com.gstahlberg.spring_training.service;

import br.com.gstahlberg.spring_training.model.Product;
import br.com.gstahlberg.spring_training.model.enumeration.EnumCategory;
import br.com.gstahlberg.spring_training.model.form.UpdateProductForm;
import br.com.gstahlberg.spring_training.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public Page<Product> findAll(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public Page<Product> findProductByCategory(Pageable pagination, EnumCategory category) {
        return repository.findProductByCategory(pagination, category);
    }

    public Optional<Product> findProductById(Long id) {
        return repository.findById(id);
    }

    public Product updateProduct(Product oldProduct, UpdateProductForm productFormForUpdate) {
        oldProduct.setName(productFormForUpdate.getName() == null ? oldProduct.getName() : productFormForUpdate.getName());
        oldProduct.setDescription(productFormForUpdate.getDescription() == null ? oldProduct.getDescription() : productFormForUpdate.getDescription());
        oldProduct.setPrice(productFormForUpdate.getPrice() == null ? oldProduct.getPrice() : productFormForUpdate.getPrice());

        return repository.save(oldProduct);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
