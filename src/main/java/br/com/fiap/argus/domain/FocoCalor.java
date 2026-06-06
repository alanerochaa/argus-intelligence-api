package br.com.fiap.argus.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
            generator = "seq_foco"
    )
    @SequenceGenerator(
            name = "seq_foco",
            sequenceName = "SEQ_FOCO_CALOR",
            allocationSize = 1
    )
    @Column(name = "ID_FOCO")
    private Long id;

    @NotNull
    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @NotNull
    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @Column(name = "FRP")
    private Double frp;

    @Column(name = "TEMPERATURA_ESTIMADA")
    private Double temperaturaEstimada;

    @Column(name = "CONFIANCA")
    private String confianca;

    @Column(name = "SATELITE")
    private String satelite;

    @Column(name = "SENSOR")
    private String sensor;

    @Column(name = "ORIGEM_DADO")
    private String origemDado;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;

    @Column(name = "STATUS")
    private String status;

    @Lob
    @Column(name = "PAYLOAD_JSON")
    private String payloadJson;

    @Column(name = "DATA_CRIACAO")
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_REGIAO")
    private Regiao regiao;

    @PrePersist
    public void prePersist() {

        dataCriacao = LocalDateTime.now();

        if (dataHora == null)
            dataHora = LocalDateTime.now();

        if (origemDado == null)
            origemDado = "NASA FIRMS";

        if (status == null)
            status = "ATIVO";
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

}