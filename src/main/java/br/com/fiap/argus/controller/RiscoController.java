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
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/riscos")
@RequiredArgsConstructor
@Tag(name = "Domínio ARGUS - RISCO", description = "Análise de risco ambiental por região monitorada.")
public class RiscoController {

    private final RegiaoRepository regiaoRepository;
    private final FocoCalorRepository focoCalorRepository;
    private final ClienteWeather clienteWeather;

    @GetMapping
    @Operation(summary = "Listar risco de todas as regiões")
    public ResponseEntity<List<RiscoResponseDTO>> listarRiscos() {
        return ResponseEntity.ok(
                regiaoRepository.findAll()
                        .stream()
                        .map(this::montarRisco)
                        .toList()
        );
    }

    @GetMapping("/regioes/{id}")
    @Operation(summary = "Calcular risco da região")
    public ResponseEntity<EntityModel<RiscoResponseDTO>> calcularRiscoRegiao(@PathVariable Long id) {
        Regiao regiao = regiaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Região não encontrada."));

        return ResponseEntity.ok(
                EntityModel.of(
                        montarRisco(regiao),
                        linkTo(methodOn(RiscoController.class).calcularRiscoRegiao(id)).withSelfRel(),
                        linkTo(methodOn(RegiaoController.class).buscarPorId(id)).withRel("regiao")
                )
        );
    }

    private RiscoResponseDTO montarRisco(Regiao regiao) {
        long quantidadeFocos = focoCalorRepository.countByRegiaoId(regiao.getId());

        return new RiscoResponseDTO(
                regiao.getId(),
                regiao.getNome(),
                regiao.getEstado(),
                regiao.getNivelRisco(),
                quantidadeFocos,
                calcularScore(regiao.getNivelRisco(), quantidadeFocos),
                buscarClima(regiao)
        );
    }

    private String buscarClima(Regiao regiao) {
        if (regiao.getLatitudeCentral() == null || regiao.getLongitudeCentral() == null) {
            return "Clima não disponível para esta região.";
        }

        return clienteWeather.buscarClima(regiao.getLatitudeCentral(), regiao.getLongitudeCentral());
    }

    private double calcularScore(String nivelRisco, long quantidadeFocos) {
        double scoreBase = switch (nivelRisco) {
            case "MEDIO" -> 35;
            case "ALTO" -> 65;
            case "CRITICO" -> 85;
            default -> 15;
        };

        return Math.min(scoreBase + Math.min(quantidadeFocos * 5, 15), 100);
    }
}