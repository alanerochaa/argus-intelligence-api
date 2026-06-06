package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.BiomaRequestDTO;
import br.com.fiap.argus.dto.response.BiomaResponseDTO;
import br.com.fiap.argus.service.BiomaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/biomas")
@RequiredArgsConstructor
@Tag(
        name = "Domínio ARGUS - Biomas",
        description = "Gerencia os biomas monitorados pelo ARGUS para análise territorial e risco ambiental."
)
public class BiomaController {

    private final BiomaService service;

    @GetMapping
    @Operation(summary = "Listar biomas", description = "Retorna todos os biomas cadastrados com links HATEOAS.")
    public ResponseEntity<CollectionModel<EntityModel<BiomaResponseDTO>>> listarTodos() {
        List<EntityModel<BiomaResponseDTO>> biomas = service.listarTodos()
                .stream()
                .map(this::adicionarLinks)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(
                        biomas,
                        linkTo(methodOn(BiomaController.class).listarTodos()).withSelfRel()
                )
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar bioma por ID", description = "Consulta um bioma específico com links HATEOAS.")
    public ResponseEntity<EntityModel<BiomaResponseDTO>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                adicionarLinks(service.buscarPorId(id))
        );
    }

    @PostMapping
    @Operation(summary = "Cadastrar bioma", description = "Cria um novo bioma para monitoramento ambiental.")
    public ResponseEntity<EntityModel<BiomaResponseDTO>> cadastrar(
            @RequestBody @Valid BiomaRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adicionarLinks(service.cadastrar(dto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar bioma", description = "Atualiza os dados de um bioma previamente cadastrado.")
    public ResponseEntity<EntityModel<BiomaResponseDTO>> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid BiomaRequestDTO dto
    ) {
        return ResponseEntity.ok(
                adicionarLinks(service.atualizar(id, dto))
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover bioma", description = "Remove permanentemente um bioma do sistema.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<BiomaResponseDTO> adicionarLinks(BiomaResponseDTO bioma) {
        return EntityModel.of(
                bioma,
                linkTo(methodOn(BiomaController.class).buscarPorId(bioma.getId())).withSelfRel(),
                linkTo(methodOn(BiomaController.class).listarTodos()).withRel("todos-biomas"),
                linkTo(methodOn(BiomaController.class).cadastrar(null)).withRel("criar-bioma"),
                linkTo(methodOn(BiomaController.class).atualizar(bioma.getId(), null)).withRel("atualizar-bioma"),
                linkTo(methodOn(BiomaController.class).deletar(bioma.getId())).withRel("remover-bioma")
        );
    }
}