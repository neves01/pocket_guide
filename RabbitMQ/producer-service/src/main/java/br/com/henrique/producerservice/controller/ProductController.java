package br.com.henrique.producerservice.controller;

import br.com.henrique.producerservice.service.ProductService;
import dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> produces(@RequestBody ProductDTO productDTO) {
        productService.produce(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
