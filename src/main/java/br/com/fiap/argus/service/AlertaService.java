package br.com.fiap.argus.service;

import br.com.fiap.argus.domain.Alerta;
import br.com.fiap.argus.domain.FocoCalor;
import br.com.fiap.argus.domain.Regiao;
import br.com.fiap.argus.dto.messaging.AlertaMensagemDTO;
import br.com.fiap.argus.dto.request.AlertaRequestDTO;
import br.com.fiap.argus.dto.response.AlertaResponseDTO;
import br.com.fiap.argus.exception.ResourceNotFoundException;
import br.com.fiap.argus.mapper.AlertaMapper;
import br.com.fiap.argus.messaging.ProdutorAlerta;
import br.com.fiap.argus.repository.AlertaRepository;
import br.com.fiap.argus.repository.FocoCalorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final FocoCalorRepository focoCalorRepository;
    private final ProdutorAlerta produtorAlerta;

    @CacheEvict(value = {"alertas", "alertaPorId"}, allEntries = true)
    public AlertaResponseDTO criar(AlertaRequestDTO dto) {
        FocoCalor focoCalor = buscarFocoCalor(dto.focoCalorId());

        Alerta alerta = AlertaMapper.toEntity(dto, focoCalor);
        Alerta alertaSalvo = alertaRepository.save(alerta);

        publicarSeNecessario(alertaSalvo);

        return AlertaMapper.toResponse(alertaSalvo);
    }

    @CacheEvict(value = {"alertas", "alertaPorId"}, allEntries = true)
    public AlertaResponseDTO gerarAutomaticamente(Long focoCalorId) {
        FocoCalor focoCalor = buscarFocoCalor(focoCalorId);

        Alerta alerta = new Alerta();

        alerta.setTitulo("Alerta automático - foco de calor detectado");
        alerta.setDescricao(montarDescricaoAutomatica(focoCalor));
        alerta.setNivel(calcularNivelRisco(focoCalor));
        alerta.setStatus("ABERTO");
        alerta.setScoreRisco(calcularScoreRisco(focoCalor));
        alerta.setRecomendacaoOperacional(montarRecomendacaoOperacional(focoCalor));
        alerta.setFocoCalor(focoCalor);

        Alerta alertaSalvo = alertaRepository.save(alerta);

        publicarSeNecessario(alertaSalvo);

        return AlertaMapper.toResponse(alertaSalvo);
    }

    @Cacheable("alertas")
    public List<AlertaResponseDTO> listar() {
        return alertaRepository.findAll()
                .stream()
                .map(AlertaMapper::toResponse)
                .toList();
    }

    @Cacheable(value = "alertaPorId", key = "#id")
    public AlertaResponseDTO buscarPorId(Long id) {
        Alerta alerta = buscarAlerta(id);
        return AlertaMapper.toResponse(alerta);
    }

    @CacheEvict(value = {"alertas", "alertaPorId"}, allEntries = true)
    public AlertaResponseDTO atualizar(Long id, AlertaRequestDTO dto) {
        Alerta alerta = buscarAlerta(id);
        FocoCalor focoCalor = buscarFocoCalor(dto.focoCalorId());

        AlertaMapper.updateEntity(alerta, dto, focoCalor);

        Alerta alertaAtualizado = alertaRepository.save(alerta);

        return AlertaMapper.toResponse(alertaAtualizado);
    }

    @CacheEvict(value = {"alertas", "alertaPorId"}, allEntries = true)
    public void remover(Long id) {
        Alerta alerta = buscarAlerta(id);
        alertaRepository.delete(alerta);
    }

    private void publicarSeNecessario(Alerta alerta) {
        if (devePublicarNaFila(alerta)) {
            AlertaMensagemDTO mensagem = montarMensagemAlerta(alerta);
            produtorAlerta.enviarAlerta(mensagem);
        }
    }

    private String montarDescricaoAutomatica(FocoCalor focoCalor) {
        return "Alerta gerado automaticamente pelo ARGUS a partir do foco de calor ID "
                + focoCalor.getId()
                + ", identificado por dados ambientais monitorados.";
    }

    private String calcularNivelRisco(FocoCalor focoCalor) {
        Double frp = focoCalor.getFrp();
        Double temperatura = focoCalor.getTemperaturaEstimada();
        Double confianca = extrairConfiancaNumerica(focoCalor);

        if (
                valorMaiorOuIgual(frp, 80.0)
                        || valorMaiorOuIgual(temperatura, 65.0)
                        || valorMaiorOuIgual(confianca, 90.0)
        ) {
            return "CRITICO";
        }

        if (
                valorMaiorOuIgual(frp, 50.0)
                        || valorMaiorOuIgual(temperatura, 50.0)
                        || valorMaiorOuIgual(confianca, 75.0)
        ) {
            return "ALTO";
        }

        if (
                valorMaiorOuIgual(frp, 25.0)
                        || valorMaiorOuIgual(temperatura, 40.0)
                        || valorMaiorOuIgual(confianca, 60.0)
        ) {
            return "MEDIO";
        }

        return "BAIXO";
    }

    private Double calcularScoreRisco(FocoCalor focoCalor) {
        double score = 0.0;

        if (focoCalor.getFrp() != null) {
            score += Math.min(focoCalor.getFrp(), 100.0) * 0.45;
        }

        if (focoCalor.getTemperaturaEstimada() != null) {
            score += Math.min(focoCalor.getTemperaturaEstimada(), 100.0) * 0.35;
        }

        Double confianca = extrairConfiancaNumerica(focoCalor);

        if (confianca != null) {
            score += Math.min(confianca, 100.0) * 0.20;
        }

        return Math.min(score, 100.0);
    }

    private Double extrairConfiancaNumerica(FocoCalor focoCalor) {
        if (focoCalor.getConfianca() == null) {
            return null;
        }

        try {
            return Double.parseDouble(
                    focoCalor.getConfianca()
                            .replace("%", "")
                            .replace(",", ".")
                            .trim()
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String montarRecomendacaoOperacional(FocoCalor focoCalor) {
        String nivel = calcularNivelRisco(focoCalor);

        return switch (nivel) {
            case "CRITICO" -> "Acionar brigada imediatamente, ampliar perímetro de monitoramento e iniciar contenção preventiva.";
            case "ALTO" -> "Priorizar monitoramento da região e preparar equipe operacional para possível acionamento.";
            case "MEDIO" -> "Manter acompanhamento preventivo e validar evolução do foco de calor nas próximas análises.";
            default -> "Registrar ocorrência para histórico e manter monitoramento periódico da região.";
        };
    }

    private boolean valorMaiorOuIgual(Double valor, Double limite) {
        return valor != null && valor >= limite;
    }

    private AlertaMensagemDTO montarMensagemAlerta(Alerta alerta) {
        FocoCalor focoCalor = alerta.getFocoCalor();
        Regiao regiao = focoCalor.getRegiao();

        return new AlertaMensagemDTO(
                alerta.getId(),
                alerta.getTitulo(),
                alerta.getDescricao(),
                alerta.getNivel(),
                alerta.getStatus(),
                alerta.getScoreRisco(),
                alerta.getRecomendacaoOperacional(),
                alerta.getDataGeracao(),

                focoCalor.getId(),
                focoCalor.getLatitude(),
                focoCalor.getLongitude(),
                focoCalor.getFrp(),
                focoCalor.getTemperaturaEstimada(),
                focoCalor.getConfianca(),
                focoCalor.getSatelite(),
                focoCalor.getSensor(),

                regiao.getId(),
                regiao.getNome(),
                regiao.getNivelRisco()
        );
    }

    private boolean devePublicarNaFila(Alerta alerta) {
        return "ALTO".equalsIgnoreCase(alerta.getNivel())
                || "CRITICO".equalsIgnoreCase(alerta.getNivel());
    }

    private Alerta buscarAlerta(Long id) {
        return alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com ID: " + id));
    }

    private FocoCalor buscarFocoCalor(Long id) {
        return focoCalorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Foco de calor não encontrado com ID: " + id));
    }
}