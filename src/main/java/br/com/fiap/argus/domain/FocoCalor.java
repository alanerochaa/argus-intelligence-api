package br.com.fiap.argus.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "FOCO_CALOR")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FocoCalor {

    @Id

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_FOCO_CALOR"
    )

    @SequenceGenerator(
            name = "SEQ_FOCO_CALOR",
            sequenceName = "SEQ_FOCO_CALOR",
            allocationSize = 1
    )

    @Column(name = "ID_FOCO")
    private Long id;

    @Column(
            name = "LATITUDE",
            nullable = false,
            precision = 10,
            scale = 6
    )
    private Double latitude;

    @Column(
            name = "LONGITUDE",
            nullable = false,
            precision = 10,
            scale = 6
    )
    private Double longitude;

    @Column(
            name = "FRP",
            precision = 10,
            scale = 2
    )
    private Double frp;

    @Column(
            name = "TEMPERATURA_ESTIMADA",
            precision = 6,
            scale = 2
    )
    private Double temperaturaEstimada;

    @Column(
            name = "CONFIANCA",
            length = 30
    )
    private String confianca;

    @Column(
            name = "SATELITE",
            length = 80
    )
    private String satelite;

    @Column(
            name = "SENSOR",
            length = 80
    )
    private String sensor;

    @Column(
            name = "ORIGEM_DADO",
            length = 80
    )
    private String origemDado;

    @Column(
            name = "DATA_HORA",
            nullable = false
    )
    private LocalDateTime dataHora;

    @Column(
            name = "STATUS",
            nullable = false,
            length = 30
    )
    private String status;

    @Lob
    @Column(name = "PAYLOAD_JSON")
    private String payloadJson;

    @Column(
            name = "DATA_CRIACAO",
            nullable = false,
            updatable = false
    )
    private LocalDateTime dataCriacao;

    // =========================
    // RELACIONAMENTO
    // =========================

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(
            name = "ID_REGIAO",
            nullable = false
    )

    private Regiao regiao;

    // =========================
    // CONTROLE AUTOMÁTICO
    // =========================

    @PrePersist
    public void prePersist() {

        this.dataCriacao = LocalDateTime.now();

        if (this.dataHora == null) {
            this.dataHora = LocalDateTime.now();
        }

        if (this.status == null) {
            this.status = "ATIVO";
        }

        if (this.origemDado == null) {
            this.origemDado = "NASA FIRMS";
        }
    }
}