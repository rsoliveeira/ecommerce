package br.com.lojaonline.ecommerce.service;

import br.com.lojaonline.ecommerce.dto.PedidoItemRequest;
import br.com.lojaonline.ecommerce.model.Cliente;
import br.com.lojaonline.ecommerce.model.Pedido;
import br.com.lojaonline.ecommerce.model.PedidoItem;
import br.com.lojaonline.ecommerce.model.Produto;
import br.com.lojaonline.ecommerce.repository.ClienteRepository;
import br.com.lojaonline.ecommerce.repository.PedidoRepository;
import br.com.lojaonline.ecommerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Pedido createOrder(Integer customerId, List<PedidoItemRequest> itemsRequest) {
        // 1. Validar cliente
        Cliente cliente = clienteRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + customerId + " não encontrado"));

        // 2. Processar itens do pedido
        List<PedidoItem> items = new ArrayList<>();
        double total = 0.0;

        for (PedidoItemRequest itemRequest : itemsRequest) {
            Produto produto = produtoRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + itemRequest.productId() + " não encontrado"));

            validateStock(produto, itemRequest.quantity());

            // Criar e configurar item do pedido
            PedidoItem item = new PedidoItem();
            item.setProduct(produto);
            item.setQuantity(itemRequest.quantity());
            item.setUnitPrice(produto.getPrice());
            items.add(item);

            // Atualizar estoque
            produto.setStock(produto.getStock() - itemRequest.quantity());
            total += produto.getPrice() * itemRequest.quantity();
        }

        // Salvar produtos atualizados (batch update)
        produtoRepository.saveAll(items.stream().map(PedidoItem::getProduct).toList());

        // 3. Criar pedido
        Pedido pedido = new Pedido();
        pedido.setCustomer(cliente);
        pedido.setItems(items);
        pedido.setOrderDate(LocalDate.now());
        pedido.setStatus("PENDENTE");
        pedido.setTotal(total);

        // Associar pedido aos itens
        items.forEach(item -> item.setPedido(pedido));

        // Salvar pedido e retornar
        return pedidoRepository.save(pedido);
    }

    private void validateStock(Produto produto, Integer quantity) {
        if (produto.getStock() < quantity) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getName());
        }
    }
}

