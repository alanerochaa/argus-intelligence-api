package br.com.fiap.argus.mapper;

import br.com.fiap.argus.domain.*;
import br.com.fiap.argus.dto.request.*;
import br.com.fiap.argus.dto.response.*;

public class RegiaoMapper {

    public static Regiao toEntity(
            RegiaoRequestDTO dto,
            Bioma bioma
    ) {

        return Regiao.builder()
                .nome(dto.nome())
                .estado(dto.estado())
                .cidadeReferencia(dto.cidadeReferencia())
                .latitudeCentral(dto.latitudeCentral())
                .longitudeCentral(dto.longitudeCentral())
                .nivelRisco(dto.nivelRisco())
                .statusMonitoramento(dto.statusMonitoramento())
                .bioma(bioma)
                .build();
    }

    public static RegiaoResponseDTO toResponse(
            Regiao regiao
    ) {

        return new RegiaoResponseDTO(
                regiao.getId(),
                regiao.getNome(),
                regiao.getEstado(),
                regiao.getCidadeReferencia(),
                regiao.getLatitudeCentral(),
                regiao.getLongitudeCentral(),
                regiao.getNivelRisco(),
                regiao.getStatusMonitoramento(),
                regiao.getDataCriacao(),
                regiao.getDataAtualizacao(),
                regiao.getBioma().getId()
        );
    }

}