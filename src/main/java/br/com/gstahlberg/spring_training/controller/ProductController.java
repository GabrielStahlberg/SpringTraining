package br.com.gstahlberg.spring_training.controller;

import br.com.gstahlberg.spring_training.model.Product;
import br.com.gstahlberg.spring_training.model.enumeration.EnumCategory;
import br.com.gstahlberg.spring_training.model.form.UpdateProductForm;
import br.com.gstahlberg.spring_training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<Product>> allProducts(@RequestParam(required = false) EnumCategory category,
                                                     @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable pagination) {

        if(category != null) {
            return ResponseEntity.ok().body(service.findProductByCategory(pagination, category));
        }

        return ResponseEntity.ok().body(service.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productById = service.findProductById(id);
        if(productById.isPresent()) {
            return ResponseEntity.ok().body(productById.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product, UriComponentsBuilder uriBuilder) {
        service.saveProduct(product);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductForm product) {

        Optional<Product> produtoToUpdateEntity = service.findProductById(id);
        if(produtoToUpdateEntity.isPresent()) {
            Product updatedProduct = service.updateProduct(produtoToUpdateEntity.get(), product);
            return ResponseEntity.ok().body(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
