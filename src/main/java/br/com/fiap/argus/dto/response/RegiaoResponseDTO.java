package br.com.fiap.argus.dto.response;

import java.time.LocalDateTime;

public record RegiaoResponseDTO(

        Long id,

        String nome,

        String estado,

        String cidadeReferencia,

        Double latitudeCentral,

        Double longitudeCentral,

        String nivelRisco,

        String statusMonitoramento,

        LocalDateTime dataCriacao,

        LocalDateTime dataAtualizacao,

        Long biomaId

) {
}