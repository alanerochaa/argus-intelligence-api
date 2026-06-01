package br.com.fiap.argus.dto.request;

import jakarta.validation.constraints.*;

public record RegiaoRequestDTO(

        @NotBlank
        String nome,

        @NotBlank
        String estado,

        String cidadeReferencia,

        Double latitudeCentral,

        Double longitudeCentral,

        String nivelRisco,

        String statusMonitoramento,

        @NotNull
        Long biomaId

) {
}