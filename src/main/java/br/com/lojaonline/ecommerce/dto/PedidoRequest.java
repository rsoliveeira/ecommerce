package br.com.lojaonline.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequest(
        @NotNull Integer customerId,
        @NotNull List<PedidoItemRequest> items
) {

}


