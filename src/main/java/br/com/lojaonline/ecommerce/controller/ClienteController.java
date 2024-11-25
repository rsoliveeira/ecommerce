package br.com.lojaonline.ecommerce.controller;

import br.com.lojaonline.ecommerce.dto.ClienteRequest;
import br.com.lojaonline.ecommerce.model.Cliente;
import br.com.lojaonline.ecommerce.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> createCustomer(@RequestBody @Valid ClienteRequest clienteRequest) {
        Cliente createdCliente = clienteService.createCustomer(clienteRequest);
        return ResponseEntity.status(201).body(createdCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCustomer(
            @PathVariable Integer id,
            @RequestBody @Valid ClienteRequest clienteRequest) {

        Cliente updatedCliente = clienteService.updateCustomer(id, clienteRequest);
        return ResponseEntity.ok(updatedCliente);
    }
}
