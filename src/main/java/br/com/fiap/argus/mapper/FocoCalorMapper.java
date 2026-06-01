package br.com.fiap.argus.mapper;

import br.com.fiap.argus.domain.FocoCalor;
import br.com.fiap.argus.domain.Regiao;
import br.com.fiap.argus.dto.request.FocoCalorRequestDTO;
import br.com.fiap.argus.dto.response.FocoCalorResponseDTO;

public class FocoCalorMapper {

    public static FocoCalor toEntity(
            FocoCalorRequestDTO dto,
            Regiao regiao
    ) {

        return FocoCalor.builder()
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .frp(dto.frp())
                .temperaturaEstimada(dto.temperaturaEstimada())
                .confianca(dto.confianca())
                .satelite(dto.satelite())
                .sensor(dto.sensor())
                .origemDado(dto.origemDado())
                .dataHora(dto.dataHora())
                .status(dto.status())
                .payloadJson(dto.payloadJson())
                .regiao(regiao)
                .build();
    }

    public static FocoCalorResponseDTO toResponse(
            FocoCalor entity
    ) {

        return new FocoCalorResponseDTO(
                entity.getId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getFrp(),
                entity.getTemperaturaEstimada(),
                entity.getConfianca(),
                entity.getSatelite(),
                entity.getSensor(),
                entity.getOrigemDado(),
                entity.getDataHora(),
                entity.getStatus(),
                entity.getPayloadJson(),
                entity.getRegiao().getId()
        );
    }

}