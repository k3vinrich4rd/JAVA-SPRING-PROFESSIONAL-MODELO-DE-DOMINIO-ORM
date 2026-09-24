package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    //Garante que o metodo execute em um contexto de transação somente leitura, otimizando a performance no banco de dados.
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        //return result.map(x -> new ProductDto(x));
        return result.map(ProductDto::new);
    }

    // Garante que o metodo execute em um contexto de transação, permitindo a persistência de alterações no banco de dados.
    @Transactional
    public ProductDto insert(ProductDto dto) {
        Product entity = new Product();
        copyDtoEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDto(entity);
    }

    //O metodo update é responsável por atualizar um produto existente no banco de dados
    // com base no ID fornecido e nos dados do DTO.
    // Ele busca a entidade Product correspondente ao ID,
    // atualiza seus atributos com os valores do DTO,
    // salva a entidade atualizada no repositório e
    // retorna um novo ProductDto representando o produto atualizado.
    @Transactional
    public ProductDto update(Long id, ProductDto dto) {
        // Busca a entidade Product correspondente ao ID fornecido no repositório.
        //getReferenceById(id) é usado para obter uma referência à entidade sem carregá-la completamente do banco de dados.
        Product entity = productRepository.getReferenceById(id);
        copyDtoEntity(dto, entity);
        return new ProductDto(entity);
    }

    // O metodo copyDtoEntity é um metodo auxiliar que copia os valores dos atributos do DTO para a entidade Product.
    private void copyDtoEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

    }


}
