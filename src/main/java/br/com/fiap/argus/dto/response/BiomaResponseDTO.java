package br.com.fiap.argus.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiomaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double areaKm2;
    private String nivelRiscoMedio;
    private String statusMonitoramento;
    private LocalDateTime dataCriacao;
}