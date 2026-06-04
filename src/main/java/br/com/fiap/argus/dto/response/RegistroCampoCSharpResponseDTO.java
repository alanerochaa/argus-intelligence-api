package br.com.fiap.argus.dto.response;

public record RegistroCampoCSharpResponseDTO(

        Long id,
        String observacao,
        String fotoUrl,
        Double latitude,
        Double longitude

) {
}