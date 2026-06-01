package br.com.fiap.argus.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record FocoCalorRequestDTO(

        @NotNull Double latitude,
        @NotNull Double longitude,

        Double frp,

        Double temperaturaEstimada,

        String confianca,

        String satelite,

        String sensor,

        String origemDado,

        LocalDateTime dataHora,

        String status,

        String payloadJson,

        @NotNull
        Long regiaoId

) {
}