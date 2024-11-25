package br.com.lojaonline.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido") // Nome da tabela no banco
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Alterado para Integer para consistência

    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false) // Chave estrangeira para 'Product'
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false) // Chave estrangeira para 'Order'
    private Pedido pedido;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity;

    @Column(name = "preco_unitario", nullable = false)
    private Double unitPrice;

    // Construtores
    public PedidoItem() {
    }

    public PedidoItem(Produto produto, Integer quantity, Double unitPrice) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        if (unitPrice == null || unitPrice < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        }
        this.produto = produto;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduct() {
        return produto;
    }

    public void setProduct(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        if (unitPrice == null || unitPrice < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        }
        this.unitPrice = unitPrice;
    }

    public Integer getProductId() {
        return produto != null ? produto.getId() : null;
    }
}
