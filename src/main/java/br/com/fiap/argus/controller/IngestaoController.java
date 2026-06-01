package br.com.fiap.argus.controller;

import br.com.fiap.argus.client.ClienteNASAFirms;
import br.com.fiap.argus.dto.response.IngestaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/ingestao")
@RequiredArgsConstructor
@Tag(
        name = "INGESTAO",
        description = "Ingestão de dados satelitais da NASA FIRMS."
)
public class IngestaoController {

    private final ClienteNASAFirms clienteNASAFirms;

    @PostMapping("/sync/24h")
    @Operation(summary = "Sincronizar focos 24h")
    public ResponseEntity<IngestaoResponseDTO> sincronizarUltimas24Horas() {

        String csv = clienteNASAFirms.buscarFocosCalorUltimas24Horas();

        return ResponseEntity.ok(
                montarResposta(csv, "24h")
        );
    }

    @PostMapping("/sync/5dias")
    @Operation(summary = "Sincronizar focos 5 dias")
    public ResponseEntity<IngestaoResponseDTO> sincronizarUltimos5Dias() {

        String csv = clienteNASAFirms.buscarFocosCalorUltimos5Dias();

        return ResponseEntity.ok(
                montarResposta(csv, "5 dias")
        );
    }

    private IngestaoResponseDTO montarResposta(
            String csv,
            String periodo
    ) {
        int totalRegistros = 0;

        if (csv != null && !csv.isBlank()) {
            totalRegistros = Math.max(
                    (int) Arrays.stream(csv.split("\\R"))
                            .filter(linha -> !linha.isBlank())
                            .count() - 1,
                    0
            );
        }

        String amostraCsv = csv == null
                ? ""
                : csv.lines()
                .limit(5)
                .reduce("", (a, b) -> a + b + "\n");

        return new IngestaoResponseDTO(
                "SUCESSO",
                "NASA FIRMS",
                periodo,
                totalRegistros,
                "Consulta realizada com sucesso.",
                amostraCsv
        );
    }
}