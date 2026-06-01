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
        name = "BIOMA",
        description = "Gerenciamento dos biomas monitorados pelo ARGUS."
)
public class BiomaController {

    private final BiomaService service;

    public BiomaController(BiomaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar biomas")
    public ResponseEntity<List<BiomaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar bioma por ID")
    public ResponseEntity<BiomaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar bioma")
    public ResponseEntity<BiomaResponseDTO> cadastrar(
            @RequestBody @Valid BiomaRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar bioma")
    public ResponseEntity<BiomaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid BiomaRequestDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover bioma")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}