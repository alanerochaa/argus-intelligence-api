package br.com.fiap.argus.dto.response;

import java.time.LocalDateTime;

public record FocoCalorResponseDTO(

        Long id,

        Double latitude,

        Double longitude,

        Double frp,

        Double temperaturaEstimada,

        String confianca,

        String satelite,

        String sensor,

        String origemDado,

        LocalDateTime dataHora,

        String status,

        String payloadJson,

        Long regiaoId

) {
}