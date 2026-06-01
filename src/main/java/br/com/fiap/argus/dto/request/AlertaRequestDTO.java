package br.com.fiap.argus.dto.request;

import jakarta.validation.constraints.*;

public record AlertaRequestDTO(

        @NotBlank(message = "O título do alerta é obrigatório.")
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres.")
        String titulo,

        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
        String descricao,

        @NotBlank(message = "O nível do alerta é obrigatório.")
        @Pattern(
                regexp = "BAIXO|MEDIO|ALTO|CRITICO",
                message = "O nível deve ser BAIXO, MEDIO, ALTO ou CRITICO."
        )
        String nivel,

        @Pattern(
                regexp = "ABERTO|EM_ANALISE|ENCAMINHADO|ENCERRADO",
                message = "O status deve ser ABERTO, EM_ANALISE, ENCAMINHADO ou ENCERRADO."
        )
        String status,

        @DecimalMin(value = "0.0", message = "O score de risco não pode ser menor que 0.")
        @DecimalMax(value = "100.0", message = "O score de risco não pode ser maior que 100.")
        Double scoreRisco,

        @Size(max = 1000, message = "A recomendação operacional deve ter no máximo 1000 caracteres.")
        String recomendacaoOperacional,

        @NotNull(message = "O ID do foco de calor é obrigatório.")
        Long focoCalorId
) {
}