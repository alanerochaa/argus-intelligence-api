package br.com.fiap.argus.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alerta")
    @SequenceGenerator(
            name = "seq_alerta",
            sequenceName = "SEQ_ALERTA",
            allocationSize = 1
    )
    @Column(name = "ID_ALERTA")
    private Long id;

    @NotBlank(message = "O título do alerta é obrigatório.")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres.")
    @Column(name = "TITULO", nullable = false, length = 150)
    private String titulo;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
    @Column(name = "DESCRICAO", length = 500)
    private String descricao;

    @NotBlank(message = "O nível do alerta é obrigatório.")
    @Pattern(
            regexp = "BAIXO|MEDIO|ALTO|CRITICO",
            message = "O nível deve ser BAIXO, MEDIO, ALTO ou CRITICO."
    )
    @Column(name = "NIVEL", nullable = false, length = 20)
    private String nivel;

    @NotBlank(message = "O status do alerta é obrigatório.")
    @Pattern(
            regexp = "ABERTO|EM_ANALISE|ENCAMINHADO|ENCERRADO",
            message = "O status deve ser ABERTO, EM_ANALISE, ENCAMINHADO ou ENCERRADO."
    )
    @Column(name = "STATUS", nullable = false, length = 30)
    private String status;

    @DecimalMin(value = "0.0", message = "O score de risco não pode ser menor que 0.")
    @DecimalMax(value = "100.0", message = "O score de risco não pode ser maior que 100.")
    @Column(name = "SCORE_RISCO")
    private Double scoreRisco;

    @Size(max = 1000, message = "A recomendação operacional deve ter no máximo 1000 caracteres.")
    @Column(name = "RECOMENDACAO_OPERACIONAL", length = 1000)
    private String recomendacaoOperacional;

    @Column(name = "DATA_GERACAO", nullable = false, updatable = false)
    private LocalDateTime dataGeracao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @NotNull(message = "O foco de calor vinculado ao alerta é obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FOCO", nullable = false)
    private FocoCalor focoCalor;

    @PrePersist
    public void prePersist() {
        this.dataGeracao = LocalDateTime.now();

        if (this.status == null || this.status.isBlank()) {
            this.status = "ABERTO";
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}