package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
// Indica que a classe é um controlador REST, responsável por lidar com requisições HTTP e retornar respostas no formato JSON.
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    // Mapeia o metodo findById para lidar com requisições HTTP GET no endpoint
    //  "/products/{id}", onde {id} é um parâmetro de caminho que representa o ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductDto dto = productService.findById(id);
        return ResponseEntity.ok(dto);

    }

    //Exemplo de busca: http://localhost:8080/products?size=12&page=1
    //Exemplo de busca: http://localhost:8080/products?size=12&page=0&sort=name
    //Exemplo de busca: http://localhost:8080/products?size=12&page=0&sort=name,desc
    //Metodo findAll com paginação, mapeado para lidar com
    // requisições HTTP GET no endpoint "/products", retornando uma página de ProductDto.
    @GetMapping()
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable) {
        Page<ProductDto> dto = productService.findAll(pageable);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<ProductDto> insert(@RequestBody ProductDto dto) {
        dto = productService.insert(dto);
        // Cria um URI para o recurso recém-criado, utilizando o ID do DTO retornado pelo serviço.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto dto) {
        dto = productService.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}
