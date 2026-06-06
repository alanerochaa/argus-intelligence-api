package br.com.fiap.argus.dto.messaging;

import java.time.LocalDateTime;

public record AlertaMensagemDTO(

        Long id,
        String titulo,
        String descricao,

        String nivel,
        String status,
        Double scoreRisco,

        String recomendacaoOperacional,
        LocalDateTime dataGeracao,

        Long focoCalorId,

        Double latitude,
        Double longitude,

        Double frp,
        Double temperaturaEstimada,

        String confianca,
        String satelite,
        String sensor,

        Long regiaoId,
        String regiaoNome,
        String nivelRiscoRegiao

) {
}