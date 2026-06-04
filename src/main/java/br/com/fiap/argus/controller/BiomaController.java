package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.BiomaRequestDTO;
import br.com.fiap.argus.dto.response.BiomaResponseDTO;
import br.com.fiap.argus.service.BiomaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biomas")

@Tag(
        name = "Domínio ARGUS - Biomas",
        description = """
        Gerencia os biomas monitorados pelo ARGUS.

        Os biomas representam regiões ambientais
        utilizadas para monitoramento territorial
        e análise de risco ambiental.
        """
)

public class BiomaController {

    private final BiomaService service;

    public BiomaController(
            BiomaService service
    ) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Listar biomas",
            description = "Retorna todos os biomas cadastrados no sistema."
    )
    public ResponseEntity<List<BiomaResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                service.listarTodos()
        );

    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar bioma por ID",
            description = "Consulta um bioma específico utilizando seu identificador."
    )
    public ResponseEntity<BiomaResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.buscarPorId(id)
        );

    }

    @PostMapping
    @Operation(
            summary = "Cadastrar bioma",
            description = "Cria um novo bioma para monitoramento ambiental."
    )
    public ResponseEntity<BiomaResponseDTO> cadastrar(

            @RequestBody
            @Valid
            BiomaRequestDTO dto

    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        service.cadastrar(dto)
                );

    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar bioma",
            description = "Atualiza os dados de um bioma previamente cadastrado."
    )
    public ResponseEntity<BiomaResponseDTO> atualizar(

            @PathVariable Long id,

            @RequestBody
            @Valid
            BiomaRequestDTO dto

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
            summary = "Remover bioma",
            description = "Remove permanentemente um bioma do sistema."
    )
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity
                .noContent()
                .build();

    }

}