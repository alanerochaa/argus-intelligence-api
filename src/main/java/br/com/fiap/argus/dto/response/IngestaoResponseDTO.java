package br.com.fiap.argus.dto.response;

public record IngestaoResponseDTO(
        String status,
        String fonte,
        String periodo,
        Integer totalRegistros,
        String mensagem,
        String amostraCsv
) {
}