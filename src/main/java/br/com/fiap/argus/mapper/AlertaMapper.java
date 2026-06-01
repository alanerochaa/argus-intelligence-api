package br.com.fiap.argus.mapper;

import br.com.fiap.argus.domain.Alerta;
import br.com.fiap.argus.domain.FocoCalor;
import br.com.fiap.argus.dto.request.AlertaRequestDTO;
import br.com.fiap.argus.dto.response.AlertaResponseDTO;

public class AlertaMapper {

    private AlertaMapper() {
    }

    public static Alerta toEntity(
            AlertaRequestDTO dto,
            FocoCalor focoCalor
    ) {
        return Alerta.builder()
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .nivel(dto.nivel())
                .status(dto.status())
                .scoreRisco(dto.scoreRisco())
                .recomendacaoOperacional(dto.recomendacaoOperacional())
                .focoCalor(focoCalor)
                .build();
    }

    public static AlertaResponseDTO toResponse(Alerta alerta) {
        return new AlertaResponseDTO(
                alerta.getId(),
                alerta.getTitulo(),
                alerta.getDescricao(),
                alerta.getNivel(),
                alerta.getStatus(),
                alerta.getScoreRisco(),
                alerta.getRecomendacaoOperacional(),
                alerta.getDataGeracao(),
                alerta.getDataAtualizacao(),
                alerta.getFocoCalor().getId()
        );
    }

    public static void updateEntity(
            Alerta alerta,
            AlertaRequestDTO dto,
            FocoCalor focoCalor
    ) {
        alerta.setTitulo(dto.titulo());
        alerta.setDescricao(dto.descricao());
        alerta.setNivel(dto.nivel());
        alerta.setStatus(dto.status());
        alerta.setScoreRisco(dto.scoreRisco());
        alerta.setRecomendacaoOperacional(dto.recomendacaoOperacional());
        alerta.setFocoCalor(focoCalor);
    }
}