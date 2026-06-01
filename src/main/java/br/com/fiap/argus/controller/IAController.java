package br.com.fiap.argus.controller;

import br.com.fiap.argus.client.ClienteIA;
import br.com.fiap.argus.dto.request.IARequestDTO;
import br.com.fiap.argus.dto.response.IAResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
@RequiredArgsConstructor
@Tag(
        name = "INTELIGENCIA ARTIFICIAL",
        description = "Integração com o serviço de Inteligência Artificial do ARGUS."
)
public class IAController {

    private final ClienteIA clienteIA;

    @PostMapping("/gerar-relatorio")
    @Operation(summary = "Gerar relatório")
    public ResponseEntity<IAResponseDTO> gerarRelatorio(
            @RequestBody @Valid IARequestDTO dto
    ) {
        return ResponseEntity.ok(
                clienteIA.gerarRelatorio(dto)
        );
    }

    @PostMapping("/consultar")
    @Operation(summary = "Consultar IA")
    public ResponseEntity<IAResponseDTO> consultar(
            @RequestBody @Valid IARequestDTO dto
    ) {
        return ResponseEntity.ok(
                clienteIA.consultarProcedimento(dto)
        );
    }
}