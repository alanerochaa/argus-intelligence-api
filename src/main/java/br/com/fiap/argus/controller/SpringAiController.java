package br.com.fiap.argus.controller;

import br.com.fiap.argus.service.ai.SpringAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/spring-ai")
@RequiredArgsConstructor
@Tag(
        name = "Spring AI - Apoio Operacional",
        description = "Recursos de inteligência artificial aplicados à análise operacional dos alertas."
)
public class SpringAiController {

    private final SpringAiService springAiService;

    @GetMapping("/recomendacao-alerta/{id}")
    @Operation(summary = "Gerar recomendação operacional com Spring AI")
    public ResponseEntity<Map<String, String>> gerarRecomendacao(@PathVariable Long id) {
        return ResponseEntity.ok(
                Map.of(
                        "origem", "Spring AI",
                        "tipo", "Recomendação operacional",
                        "recomendacao", springAiService.gerarRecomendacaoOperacional(id)
                )
        );
    }
}