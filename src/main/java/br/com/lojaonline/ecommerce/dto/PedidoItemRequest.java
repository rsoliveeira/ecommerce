package br.com.lojaonline.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PedidoItemRequest(
        @NotNull Integer productId,
        @Min(1) Integer quantity
) {

}
