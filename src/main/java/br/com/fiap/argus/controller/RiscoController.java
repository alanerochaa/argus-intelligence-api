package br.com.fiap.argus.controller;

import br.com.fiap.argus.client.ClienteWeather;
import br.com.fiap.argus.domain.Regiao;
import br.com.fiap.argus.dto.response.RiscoResponseDTO;
import br.com.fiap.argus.repository.FocoCalorRepository;
import br.com.fiap.argus.repository.RegiaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/riscos")
@RequiredArgsConstructor
@Tag(
        name = "Domínio ARGUS -RISCO",
        description = "Análise de risco ambiental por região monitorada."
)
public class RiscoController {

    private final RegiaoRepository regiaoRepository;
    private final FocoCalorRepository focoCalorRepository;
    private final ClienteWeather clienteWeather;

    @GetMapping("/regioes/{id}")
    @Operation(summary = "Calcular risco da região")
    public ResponseEntity<RiscoResponseDTO> calcularRiscoRegiao(
            @PathVariable Long id
    ) {
        Regiao regiao = regiaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Região não encontrada."));

        long quantidadeFocos = focoCalorRepository.countByRegiaoId(id);

        double score = calcularScore(
                regiao.getNivelRisco(),
                quantidadeFocos
        );

        String clima = null;

        if (
                regiao.getLatitudeCentral() != null &&
                        regiao.getLongitudeCentral() != null
        ) {
            clima = clienteWeather.buscarClima(
                    regiao.getLatitudeCentral(),
                    regiao.getLongitudeCentral()
            );
        }

        return ResponseEntity.ok(
                new RiscoResponseDTO(
                        regiao.getId(),
                        regiao.getNome(),
                        regiao.getEstado(),
                        regiao.getNivelRisco(),
                        quantidadeFocos,
                        score,
                        clima == null
                                ? "Clima não disponível para esta região."
                                : clima
                )
        );
    }

    private double calcularScore(
            String nivelRisco,
            long quantidadeFocos
    ) {
        double scoreBase = switch (nivelRisco) {
            case "MEDIO" -> 35.0;
            case "ALTO" -> 65.0;
            case "CRITICO" -> 85.0;
            default -> 15.0;
        };

        double incrementoPorFoco = Math.min(
                quantidadeFocos * 5.0,
                15.0
        );

        return Math.min(
                scoreBase + incrementoPorFoco,
                100.0
        );
    }
}