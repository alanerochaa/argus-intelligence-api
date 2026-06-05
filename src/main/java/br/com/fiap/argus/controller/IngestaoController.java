package br.com.fiap.argus.controller;

import br.com.fiap.argus.service.IngestaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ingestao")
@RequiredArgsConstructor
@Tag(
        name = "Integrações Externas - NASA FIRMS",
        description = "Consome dados de focos de calor da API externa NASA FIRMS."
)
public class IngestaoController {

    private final IngestaoService ingestaoService;

    @Operation(
            summary = "Status da integração NASA FIRMS",
            description = """
            Endpoint utilizado para validar se a integração
            com a NASA FIRMS está disponível.
            Não executa ingestão.
            """
    )
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status() {

        Map<String, Object> response =
                new LinkedHashMap<>();

        response.put(
                "servico",
                "NASA FIRMS"
        );

        response.put(
                "status",
                "ONLINE"
        );

        response.put(
                "operacao",
                "Disponível para ingestão"
        );

        response.put(
                "timestamp",
                LocalDateTime.now()
        );

        return ResponseEntity.ok(
                response
        );

    }

    @Operation(
            summary = "Consumir focos de calor da NASA (24 horas)",
            description = """
            Consome focos de calor detectados pela NASA
            nas últimas 24 horas.
            """
    )
    @PostMapping("/sync/24h")
    public ResponseEntity<String> sincronizar24Horas() {

        return ResponseEntity.ok(
                ingestaoService
                        .consumirFocosCalorUltimas24Horas()
        );

    }

    @Operation(
            summary = "Consumir focos de calor da NASA (5 dias)",
            description = """
            Consome focos de calor detectados pela NASA
            nos últimos 5 dias.
            """
    )
    @PostMapping("/sync/5dias")
    public ResponseEntity<String> sincronizar5Dias() {

        return ResponseEntity.ok(
                ingestaoService
                        .consumirFocosCalorUltimos5Dias()
        );

    }

}