package br.com.fiap.argus.domain;

import jakarta.persistence.*;
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
            generator = "SEQ_REGIAO"
    )
    @SequenceGenerator(
            name = "SEQ_REGIAO",
            sequenceName = "SEQ_REGIAO",
            allocationSize = 1
    )
    @Column(name = "ID_REGIAO")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 150)
    private String nome;

    @Column(name = "ESTADO", nullable = false, length = 2)
    private String estado;

    @Column(name = "CIDADE_REFERENCIA", length = 120)
    private String cidadeReferencia;

    @Column(name = "LATITUDE_CENTRAL")
    private Double latitudeCentral;

    @Column(name = "LONGITUDE_CENTRAL")
    private Double longitudeCentral;

    @Column(name = "NIVEL_RISCO", nullable = false, length = 20)
    private String nivelRisco;

    @Column(name = "STATUS_MONITORAMENTO", nullable = false, length = 30)
    private String statusMonitoramento;

    @Column(name = "DATA_CRIACAO", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BIOMA", nullable = false)
    private Bioma bioma;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();

        if (this.nivelRisco == null) {
            this.nivelRisco = "BAIXO";
        }

        if (this.statusMonitoramento == null) {
            this.statusMonitoramento = "ATIVA";
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}