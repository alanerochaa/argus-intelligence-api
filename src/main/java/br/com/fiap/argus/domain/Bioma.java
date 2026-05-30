package br.com.fiap.argus.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BIOMA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bioma {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_BIOMA"
    )
    @SequenceGenerator(
            name = "SEQ_BIOMA",
            sequenceName = "SEQ_BIOMA",
            allocationSize = 1
    )
    @Column(name = "ID_BIOMA")
    private Long id;

    @Column(name = "NOME", nullable = false, unique = true, length = 100)
    private String nome;

    @Column(name = "DESCRICAO", length = 500)
    private String descricao;

    @Column(name = "AREA_KM2")
    private Double areaKm2;

    @Column(name = "NIVEL_RISCO_MEDIO", nullable = false, length = 30)
    private String nivelRiscoMedio;

    @Column(name = "STATUS_MONITORAMENTO", nullable = false, length = 30)
    private String statusMonitoramento;

    @Column(name = "DATA_CRIACAO", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();

        if (this.nivelRiscoMedio == null) {
            this.nivelRiscoMedio = "BAIXO";
        }

        if (this.statusMonitoramento == null) {
            this.statusMonitoramento = "ATIVO";
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}