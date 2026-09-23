package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    //Garante que o metodo execute em um contexto de transação somente leitura, otimizando a performance no banco de dados.
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDto(product);

    }


}
