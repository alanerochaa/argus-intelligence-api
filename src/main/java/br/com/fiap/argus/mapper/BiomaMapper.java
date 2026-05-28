package br.com.fiap.argus.mapper;

import br.com.fiap.argus.domain.Bioma;

import br.com.fiap.argus.dto.request.BiomaRequestDTO;
import br.com.fiap.argus.dto.response.BiomaResponseDTO;
import org.springframework.stereotype.Component;

@Component

public class BiomaMapper {

    public Bioma toEntity(BiomaRequestDTO dto) {

        return Bioma.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .areaKm2(dto.getAreaKm2())
                .nivelRiscoMedio(dto.getNivelRiscoMedio())
                .statusMonitoramento(dto.getStatusMonitoramento())
                .build();
    }

    public BiomaResponseDTO toResponse(Bioma entity) {

        return BiomaResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .areaKm2(entity.getAreaKm2())
                .nivelRiscoMedio(entity.getNivelRiscoMedio())
                .statusMonitoramento(entity.getStatusMonitoramento())
                .dataCriacao(entity.getDataCriacao())
                .build();
    }

    public void updateEntity(
            Bioma entity,
            BiomaRequestDTO dto
    ) {

        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setAreaKm2(dto.getAreaKm2());
        entity.setNivelRiscoMedio(dto.getNivelRiscoMedio());
        entity.setStatusMonitoramento(dto.getStatusMonitoramento());
    }
}