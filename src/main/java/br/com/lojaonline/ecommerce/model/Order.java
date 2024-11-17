package br.com.lojaonline.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Entity
@Table(name = "pedido")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer id;

    @Column(name = "id_usuario")
    private Integer customerId;

    @Column(name = "data_pedido")
    private Date orderDate;

    private String status;
}

