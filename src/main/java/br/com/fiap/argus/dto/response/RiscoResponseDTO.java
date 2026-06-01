package br.com.fiap.argus.dto.response;

public record RiscoResponseDTO(

        Long regiaoId,

        String nome,

        String estado,

        String nivelRiscoBase,

        Long quantidadeFocos,

        Double scoreRisco,

        String clima

) {
}