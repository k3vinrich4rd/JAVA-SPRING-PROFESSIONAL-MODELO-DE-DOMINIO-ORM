package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Indica que a classe é um controlador REST, responsável por lidar com requisições HTTP e retornar respostas no formato JSON.
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    // Mapeia o metodo findById para lidar com requisições HTTP GET no endpoint
    //  "/products/{id}", onde {id} é um parâmetro de caminho que representa o ID
    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);

    }


}
