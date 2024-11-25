package br.com.lojaonline.ecommerce.service;

import br.com.lojaonline.ecommerce.dto.ClienteRequest;
import br.com.lojaonline.ecommerce.model.Cliente;
import br.com.lojaonline.ecommerce.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente createCustomer(ClienteRequest clienteRequest) {
        if (clienteRepository.existsByEmail(clienteRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email já está em uso"
            );
        }

        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.nome());
        cliente.setEmail(clienteRequest.email());
        cliente.setSenha(clienteRequest.senha());

        return clienteRepository.save(cliente);
    }

    public Cliente updateCustomer(Integer id, ClienteRequest clienteRequest) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente com ID " + id + " não encontrado"
                ));

        cliente.setNome(clienteRequest.nome());
        cliente.setEmail(clienteRequest.email());
        cliente.setSenha(clienteRequest.senha());

        return clienteRepository.save(cliente);
    }
}
