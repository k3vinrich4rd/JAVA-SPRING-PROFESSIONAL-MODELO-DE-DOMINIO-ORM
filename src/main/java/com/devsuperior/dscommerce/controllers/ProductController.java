package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    //Exemplo de busca: http://localhost:8080/products?size=12&page=1
    //Exemplo de busca: http://localhost:8080/products?size=12&page=0&sort=name
    //Exemplo de busca: http://localhost:8080/products?size=12&page=0&sort=name,desc
    //Metodo findAll com paginação, mapeado para lidar com
    // requisições HTTP GET no endpoint "/products", retornando uma página de ProductDto.
    @GetMapping()
    public Page<ProductDto> findAll(Pageable pageable) {
        return productService.findAll(pageable);

    }

    @PostMapping
    public ProductDto insert(@RequestBody ProductDto dto) {
        return productService.insert(dto);
    }


}
