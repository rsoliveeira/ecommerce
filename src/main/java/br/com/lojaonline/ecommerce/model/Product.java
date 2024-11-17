package br.com.lojaonline.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item") // Nome do campo na tabela
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "preco")
    private Double price;

    @Column(name = "estoque")
    private Integer stock;
}
