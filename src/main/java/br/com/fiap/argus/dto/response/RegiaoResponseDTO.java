package br.com.fiap.argus.dto.response;

import lombok.*;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegiaoResponseDTO
        extends RepresentationModel<RegiaoResponseDTO> {

    private Long id;

    private String nome;

    private String estado;

    private String cidadeReferencia;

    private Double latitudeCentral;

    private Double longitudeCentral;

    private String nivelRisco;

    private String statusMonitoramento;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private Long biomaId;

}