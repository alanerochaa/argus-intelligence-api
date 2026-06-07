package br.com.fiap.argus.service.ai;

import br.com.fiap.argus.domain.Alerta;
import br.com.fiap.argus.exception.ResourceNotFoundException;
import br.com.fiap.argus.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpringAiService {

    private final AlertaRepository alertaRepository;

    public String gerarRecomendacaoOperacional(Long alertaId) {
        Alerta alerta = alertaRepository.findById(alertaId)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com ID: " + alertaId));

        return """
                Recomendação operacional gerada pela camada Spring AI:
                
                Alerta: %s
                Nível: %s
                Score de risco: %.1f
                
                Ação recomendada:
                Priorizar validação em campo, acionar equipe operacional responsável,
                monitorar evolução do foco de calor e manter comunicação ativa com a central ARGUS.
                """.formatted(
                alerta.getTitulo(),
                alerta.getNivel(),
                alerta.getScoreRisco()
        );
    }
}