package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.RegiaoRequestDTO;
import br.com.fiap.argus.dto.response.RegiaoResponseDTO;
import br.com.fiap.argus.service.RegiaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regioes")
@RequiredArgsConstructor
@Tag(
        name = "REGIAO",
        description = "Gerenciamento das regiões monitoradas pelo ARGUS."
)
public class RegiaoController {

    private final RegiaoService service;

    @PostMapping
    @Operation(summary = "Cadastrar região")
    public ResponseEntity<RegiaoResponseDTO> criar(
            @RequestBody @Valid RegiaoRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar regiões")
    public ResponseEntity<List<RegiaoResponseDTO>> listar() {
        return ResponseEntity.ok(
                service.listar()
        );
    }
}