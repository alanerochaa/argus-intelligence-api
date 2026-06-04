package br.com.fiap.argus.dto.request;

import jakarta.validation.constraints.*;

public record IARequestRelatorioDTO(

        @NotBlank
        String localizacao,

        @NotBlank
        String tipoVegetacao,

        @NotBlank
        String tamanhoEstimado,

        @NotBlank
        String acoesTomadas,

        @NotBlank
        String recursosUtilizados,

        @NotNull
        Integer numeroBrigadistas,

        @NotBlank
        String nivelRisco

) {
}