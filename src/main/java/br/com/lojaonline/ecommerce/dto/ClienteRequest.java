package br.com.lojaonline.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank @Email(message = "Email inválido") String email,
        @NotBlank(message = "Senha é obrigatória") String senha
) {}
