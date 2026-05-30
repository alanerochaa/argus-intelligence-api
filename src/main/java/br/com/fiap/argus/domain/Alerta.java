package br.com.fiap.argus.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ALERTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alerta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ALERTA"
    )
    @SequenceGenerator(
            name = "SEQ_ALERTA",
            sequenceName = "SEQ_ALERTA",
            allocationSize = 1
    )
    @Column(name = "ID_ALERTA")
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 150)
    private String titulo;

    @Column(name = "DESCRICAO", length = 500)
    private String descricao;

    @Column(name = "NIVEL", nullable = false, length = 20)
    private String nivel;

    @Column(name = "STATUS", nullable = false, length = 30)
    private String status;

    @Column(name = "SCORE_RISCO")
    private Double scoreRisco;

    @Column(name = "RECOMENDACAO_OPERACIONAL", length = 1000)
    private String recomendacaoOperacional;

    @Column(name = "DATA_GERACAO", nullable = false, updatable = false)
    private LocalDateTime dataGeracao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FOCO", nullable = false)
    private FocoCalor focoCalor;

    @PrePersist
    public void prePersist() {
        this.dataGeracao = LocalDateTime.now();

        if (this.status == null) {
            this.status = "ABERTO";
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}