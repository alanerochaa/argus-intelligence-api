package br.com.fiap.argus.dto.response;

import java.time.OffsetDateTime;

public record CSharpLoginResponseDTO(
        String token,
        OffsetDateTime expiraEm
) {
}