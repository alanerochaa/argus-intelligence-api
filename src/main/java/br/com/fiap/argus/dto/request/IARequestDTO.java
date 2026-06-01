package br.com.fiap.argus.dto.request;

import jakarta.validation.constraints.NotBlank;

public record IARequestDTO(

        @NotBlank(message = "A mensagem é obrigatória.")
        String mensagem

) {
}