package br.com.gstahlberg.spring_training.service;

import br.com.gstahlberg.spring_training.model.Product;
import br.com.gstahlberg.spring_training.model.form.UpdateProductForm;
import br.com.gstahlberg.spring_training.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
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
