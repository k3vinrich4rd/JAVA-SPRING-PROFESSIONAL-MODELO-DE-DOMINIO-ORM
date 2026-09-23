package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
// Indica que a classe é um controlador REST, responsável por lidar com requisições HTTP e retornar respostas no formato JSON.
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String teste() {
        Optional<Product> result = productRepository.findById(1L);
        Product product = result.get();
        return product.getName();
    }


}
