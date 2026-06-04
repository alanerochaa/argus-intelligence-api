package br.com.fiap.argus.dto.request;

import jakarta.validation.constraints.NotBlank;

public record IARequestConsultaDTO(

        @NotBlank
        String pergunta

) {
}