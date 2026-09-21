package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT") // Permite que o campo description seja armazenado como um tipo de dado TEXT no banco de dados, permitindo armazenar textos longos.
    private String description;

    private Double price;

    //Jpa por padrão converte nomes de campos que estão em camel case para snakecase
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private final Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product") // Indica que a relação entre Product e OrdemItem é mapeada pelo atributo
    // "product" da classe OrdemItemPK, que é a chave primária composta da entidade OrdemItem.
    private final Set<OrdemItem> items = new HashSet<>();

    public Product() {

    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<OrdemItem> getItems() {
        return items;
    }

    public List<Order> getOrders() {
        // Retorna uma lista de pedidos associados a este produto, obtida a partir dos itens de pedido (OrdemItem) relacionados a este produto.
        //Outra forma de implementar utilizando o map e method reference com toList() para coletar os pedidos em uma lista.
        return items.stream().map(OrdemItem::getOrder).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
