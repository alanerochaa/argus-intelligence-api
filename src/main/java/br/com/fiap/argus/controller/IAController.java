package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.IARequestConsultaDTO;
import br.com.fiap.argus.dto.request.IARequestRelatorioDTO;
import br.com.fiap.argus.dto.response.IAResponseConsultaDTO;
import br.com.fiap.argus.dto.response.IAResponseRelatorioDTO;
import br.com.fiap.argus.service.IAService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
@RequiredArgsConstructor
@Tag(
        name = "Integrações Externas - API IA",
        description = "Consome a API externa de Inteligência Artificial do ARGUS."
)
public class IAController {

    private final IAService iaService;

    @Operation(
            summary = "Consumir geração de relatório da API IA",
            description = """
            Envia dados estruturados de uma ocorrência para a API externa de IA
            e retorna um relatório técnico gerado automaticamente.
            """
    )
    @PostMapping("/gerar-relatorio")
    public IAResponseRelatorioDTO gerarRelatorio(
            @RequestBody @Valid IARequestRelatorioDTO dto
    ) {
        return iaService.gerarRelatorio(dto);
    }

    @Operation(
            summary = "Consumir consulta RAG da API IA",
            description = """
            Envia uma pergunta para a API externa de IA
            e retorna uma resposta baseada em procedimentos operacionais.
            """
    )
    @PostMapping("/consultar")
    public IAResponseConsultaDTO consultar(
            @RequestBody @Valid IARequestConsultaDTO dto
    ) {
        return iaService.consultar(dto);
    }

    @Operation(
            summary = "Verificar status da API IA externa",
            description = """
            Consulta o endpoint de health da API externa de IA
            para validar se o serviço está disponível.
            """
    )
    @GetMapping("/health")
    public String health() {
        return iaService.health();
    }
}