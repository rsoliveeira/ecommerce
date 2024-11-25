package br.com.lojaonline.ecommerce.controller;

import br.com.lojaonline.ecommerce.dto.PedidoItemRequest;
import br.com.lojaonline.ecommerce.dto.PedidoRequest;
import br.com.lojaonline.ecommerce.dto.PedidoResponse;
import br.com.lojaonline.ecommerce.model.Pedido;
import br.com.lojaonline.ecommerce.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> createOrder(@RequestBody @Valid PedidoRequest pedidoRequest) {
        Integer customerId = pedidoRequest.customerId(); // Obtém o ID do cliente
        List<PedidoItemRequest> items = pedidoRequest.items(); // Obtém os itens do pedido

        // Cria o pedido
        Pedido newPedido = pedidoService.createOrder(customerId, items);

        // Converte a entidade para um DTO de resposta
        PedidoResponse response = new PedidoResponse(
                newPedido.getId(),
                newPedido.getCustomer().getId(),
                newPedido.getOrderDate(),
                newPedido.getStatus(),
                newPedido.getTotal()
        );

        return ResponseEntity.ok(response);
    }
}
