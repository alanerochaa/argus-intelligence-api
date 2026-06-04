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
        name = "Domínio ARGUS - Alertas",
        description = """
        Gerencia os alertas ambientais do ARGUS.

        Os alertas representam eventos de risco
        gerados a partir dos focos de calor monitorados.
        """
)

public class AlertaController {

    private final AlertaService service;

    @GetMapping
    @Operation(
            summary = "Listar alertas",
            description = "Retorna todos os alertas cadastrados no sistema."
    )
    public ResponseEntity<List<AlertaResponseDTO>> listar() {

        return ResponseEntity.ok(
                service.listar()
        );

    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar alerta por ID",
            description = "Consulta um alerta específico utilizando seu identificador."
    )
    public ResponseEntity<AlertaResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.buscarPorId(id)
        );

    }

    @PostMapping
    @Operation(
            summary = "Cadastrar alerta",
            description = "Cria um novo alerta ambiental no ARGUS."
    )
    public ResponseEntity<AlertaResponseDTO> criar(

            @RequestBody
            @Valid
            AlertaRequestDTO dto

    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        service.criar(dto)
                );

    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar alerta",
            description = "Atualiza os dados de um alerta já existente."
    )
    public ResponseEntity<AlertaResponseDTO> atualizar(

            @PathVariable Long id,

            @RequestBody
            @Valid
            AlertaRequestDTO dto

    ) {

        return ResponseEntity.ok(
                service.atualizar(
                        id,
                        dto
                )
        );

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remover alerta",
            description = "Remove permanentemente um alerta do sistema."
    )
    public ResponseEntity<Void> remover(
            @PathVariable Long id
    ) {

        service.remover(id);

        return ResponseEntity
                .noContent()
                .build();

    }

}