package com.devsuperior.dscommerce.entities;

import com.devsuperior.dscommerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") // Para que o banco de dados não adicione o fuso horário,
    // por padrão o PostgreSQL adiciona o fuso horário,
    // mas o Java não trabalha com fuso horário, então é necessário adicionar essa anotação para que o banco de dados não adicione o fuso horário.
    //Fuso horário é a diferença de tempo entre dois lugares do mundo, por exemplo, o Brasil está no fuso horário GMT-3, enquanto a Inglaterra está no fuso horário GMT+0.
    private Instant moment;

    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "id.order") // Indica que a relação entre Order e OrdemItem é mapeada pelo atributo
    // "order" da classe OrdemItemPK, que é a chave primária composta da entidade OrdemItem.
    private Set<OrdemItem> items = new HashSet<>();

    public Order() {

    }

    public Order(Long id, Instant moment, OrderStatus status, User client, Payment payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


    public Set<OrdemItem> getItems() {
        return items;
    }

    // Metodo para retornar uma lista de produtos do pedido
    // Ele percorre a lista de itens do pedido e retorna uma lista de produtos associados a cada item.
    // Ele utiliza o método map() para transformar cada item em um produto e o método toList() para coletar os produtos em uma lista.
    public List<Product> getProducts() {
        return items.stream().map(x -> x.getProduct()).toList();


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
