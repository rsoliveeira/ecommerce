package br.com.lojaonline.ecommerce.dto;

import java.time.LocalDate;

public record PedidoResponse(
        Integer orderId,
        Integer customerId,
        LocalDate orderDate,
        String status,
        Double total
) {}

