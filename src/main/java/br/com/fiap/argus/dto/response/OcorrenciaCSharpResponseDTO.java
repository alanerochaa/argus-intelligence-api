package br.com.fiap.argus.dto.response;

public record OcorrenciaCSharpResponseDTO(

        Long id,
        String descricao,
        String status,
        Long alertaId,
        Long brigadistaId

) {
}