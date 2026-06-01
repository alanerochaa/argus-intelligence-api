package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.FocoCalorRequestDTO;
import br.com.fiap.argus.dto.response.FocoCalorResponseDTO;
import br.com.fiap.argus.service.FocoCalorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/focos")
@RequiredArgsConstructor
@Tag(
        name = "FOCO CALOR",
        description = "Gerenciamento dos focos de calor monitorados pelo ARGUS."
)
public class FocoCalorController {

    private final FocoCalorService service;

    @PostMapping
    @Operation(summary = "Cadastrar foco de calor")
    public ResponseEntity<FocoCalorResponseDTO> criar(
            @RequestBody @Valid FocoCalorRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar focos de calor")
    public ResponseEntity<List<FocoCalorResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}