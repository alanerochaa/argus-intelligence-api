package br.com.fiap.argus.dto.response;

import java.time.LocalDateTime;

public record AlertaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String nivel,
        String status,
        Double scoreRisco,
        String recomendacaoOperacional,
        LocalDateTime dataGeracao,
        LocalDateTime dataAtualizacao,
        Long focoCalorId
) {
}