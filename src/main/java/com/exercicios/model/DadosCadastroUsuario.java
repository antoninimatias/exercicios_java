package com.exercicios.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email
) {
}
