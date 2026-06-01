package br.com.fiap.argus.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "REGIAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Regiao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_regiao"
    )
    @SequenceGenerator(
            name = "seq_regiao",
            sequenceName = "SEQ_REGIAO",
            allocationSize = 1
    )
    @Column(name = "ID_REGIAO")
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "NOME", nullable = false)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Size(max = 120)
    @Column(name = "CIDADE_REFERENCIA")
    private String cidadeReferencia;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    @Column(name = "LATITUDE_CENTRAL")
    private Double latitudeCentral;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @Column(name = "LONGITUDE_CENTRAL")
    private Double longitudeCentral;

    @Pattern(
            regexp = "BAIXO|MEDIO|ALTO|CRITICO"
    )
    @Column(name = "NIVEL_RISCO")
    private String nivelRisco;

    @Pattern(
            regexp = "ATIVA|INATIVA|EM_ANALISE"
    )
    @Column(name = "STATUS_MONITORAMENTO")
    private String statusMonitoramento;

    @Column(
            name = "DATA_CRIACAO",
            updatable = false
    )
    private LocalDateTime dataCriacao;

    @Column(
            name = "DATA_ATUALIZACAO"
    )
    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ID_BIOMA",
            nullable = false
    )
    private Bioma bioma;

    @PrePersist
    public void prePersist() {

        dataCriacao = LocalDateTime.now();

        if (nivelRisco == null)
            nivelRisco = "BAIXO";

        if (statusMonitoramento == null)
            statusMonitoramento = "ATIVA";
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

}