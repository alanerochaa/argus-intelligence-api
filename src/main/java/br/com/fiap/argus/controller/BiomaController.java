package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.BiomaRequestDTO;
import br.com.fiap.argus.dto.response.BiomaResponseDTO;
import br.com.fiap.argus.service.BiomaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biomas")
public class BiomaController {

    private final BiomaService service;

    public BiomaController(BiomaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BiomaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BiomaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<BiomaResponseDTO> cadastrar(
            @RequestBody @Valid BiomaRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BiomaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid BiomaRequestDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}