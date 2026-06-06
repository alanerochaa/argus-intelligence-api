package br.com.fiap.argus.mapper;

import br.com.fiap.argus.domain.Bioma;
import br.com.fiap.argus.domain.Regiao;

import br.com.fiap.argus.dto.request.RegiaoRequestDTO;
import br.com.fiap.argus.dto.response.RegiaoResponseDTO;

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

        return RegiaoResponseDTO.builder()
                .id(regiao.getId())
                .nome(regiao.getNome())
                .estado(regiao.getEstado())
                .cidadeReferencia(regiao.getCidadeReferencia())
                .latitudeCentral(regiao.getLatitudeCentral())
                .longitudeCentral(regiao.getLongitudeCentral())
                .nivelRisco(regiao.getNivelRisco())
                .statusMonitoramento(regiao.getStatusMonitoramento())
                .dataCriacao(regiao.getDataCriacao())
                .dataAtualizacao(regiao.getDataAtualizacao())
                .biomaId(regiao.getBioma().getId())
                .build();

    }

    public static void updateEntity(
            Regiao regiao,
            RegiaoRequestDTO dto,
            Bioma bioma
    ) {

        regiao.setNome(dto.nome());
        regiao.setEstado(dto.estado());
        regiao.setCidadeReferencia(dto.cidadeReferencia());

        regiao.setLatitudeCentral(dto.latitudeCentral());
        regiao.setLongitudeCentral(dto.longitudeCentral());

        regiao.setNivelRisco(dto.nivelRisco());
        regiao.setStatusMonitoramento(dto.statusMonitoramento());

        regiao.setBioma(bioma);

    }

}