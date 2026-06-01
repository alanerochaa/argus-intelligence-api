package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.AlertaRequestDTO;
import br.com.fiap.argus.dto.response.AlertaResponseDTO;
import br.com.fiap.argus.service.AlertaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
@Tag(
        name = "ALERTA",
        description = """
        API responsável pelo gerenciamento dos alertas ambientais do ARGUS.

        Os alertas representam eventos de risco gerados a partir dos focos de calor monitorados.
        """
)
public class AlertaController {

    private final AlertaService alertaService;

    @PostMapping
    @Operation(
            summary = "Cadastrar alerta"
    )
    public ResponseEntity<AlertaResponseDTO> criar(
            @RequestBody
            @Valid
            AlertaRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        alertaService.criar(dto)
                );
    }

    @GetMapping
    @Operation(
            summary = "Listar alertas"
    )
    public ResponseEntity<
            List<AlertaResponseDTO>
            > listar() {

        return ResponseEntity.ok(
                alertaService.listar()
        );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar alerta por ID"
    )
    public ResponseEntity<AlertaResponseDTO>
    buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                alertaService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar alerta"
    )
    public ResponseEntity<AlertaResponseDTO>
    atualizar(

            @PathVariable Long id,

            @RequestBody
            @Valid
            AlertaRequestDTO dto

    ) {

        return ResponseEntity.ok(
                alertaService.atualizar(
                        id,
                        dto
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remover alerta"
    )
    public ResponseEntity<Void>
    remover(
            @PathVariable Long id
    ) {

        alertaService.remover(
                id
        );

        return ResponseEntity.noContent()
                .build();
    }

}