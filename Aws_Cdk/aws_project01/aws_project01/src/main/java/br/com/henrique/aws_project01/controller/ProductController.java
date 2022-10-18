package br.com.henrique.aws_project01.controller;

import br.com.henrique.aws_project01.enums.EventType;
import br.com.henrique.aws_project01.model.Product;
import br.com.henrique.aws_project01.model.ProductEvent;
import br.com.henrique.aws_project01.repository.ProductRepository;
import br.com.henrique.aws_project01.service.ProductPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPublisherService productPublisherService;

    @GetMapping
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<Product>(optionalProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productCreated = productRepository.save(product);
        productPublisherService.publishProductEvent(productCreated, EventType.PRODUCT_CREATED, "username1");
        return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable long id) {
        if (productRepository.existsById(product.getId())) {
            product.setId(id);
            Product productUpdated = productRepository.save(product);
            productPublisherService.publishProductEvent(productUpdated, EventType.PRODUCT_UPDATED, "username2");
            return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.delete(product);
            productPublisherService.publishProductEvent(product, EventType.PRODUCT_DELETED, "username3");
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String code) {
        Optional<Product> optionalProduct = productRepository.findByCode(code);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<Product>(optionalProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
