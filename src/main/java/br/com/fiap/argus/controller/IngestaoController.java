package br.com.fiap.argus.controller;

import br.com.fiap.argus.service.IngestaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

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
            summary = "Consumir focos de calor da NASA (24 horas)",
            description = """
            Consome focos de calor detectados pela NASA
            nas últimas 24 horas.
            """
    )
    @PostMapping("/sync/24h")
    public String sincronizar24Horas() {

        return ingestaoService
                .consumirFocosCalorUltimas24Horas();

    }

    @Operation(
            summary = "Consumir focos de calor da NASA (5 dias)",
            description = """
            Consome focos de calor detectados pela NASA
            nos últimos 5 dias.
            """
    )
    @PostMapping("/sync/5dias")
    public String sincronizar5Dias() {

        return ingestaoService
                .consumirFocosCalorUltimos5Dias();

    }

}