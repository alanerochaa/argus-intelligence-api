package br.com.fiap.argus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiomaRequestDTO {

    @NotBlank(message = "Nome do bioma é obrigatório")
    @Size(max = 100)
    private String nome;

    @Size(max = 500)
    private String descricao;

    @Positive(message = "Área deve ser maior que zero")
    private Double areaKm2;

    private String nivelRiscoMedio;

    private String statusMonitoramento;
}