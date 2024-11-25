package br.com.lojaonline.ecommerce.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Consistência no tipo

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false) // Cliente associado ao pedido
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> items; // Itens do pedido

    @Column(name = "data_pedido", nullable = false)
    private LocalDate orderDate;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "total", nullable = false)
    private Double total;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCustomer() {
        return cliente;
    }

    public void setCustomer(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PedidoItem> getItems() {
        return items;
    }

    public void setItems(List<PedidoItem> items) {
        this.items = items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
