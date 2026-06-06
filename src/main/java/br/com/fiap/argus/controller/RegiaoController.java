package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.RegiaoRequestDTO;
import br.com.fiap.argus.dto.response.RegiaoResponseDTO;
import br.com.fiap.argus.service.RegiaoService;

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
@RequestMapping("/api/regioes")
@RequiredArgsConstructor

@Tag(
        name = "Domínio ARGUS - REGIÃO",
        description = "Gerenciamento das regiões monitoradas pelo ARGUS."
)

public class RegiaoController {

    private final RegiaoService service;

    @PostMapping
    @Operation(summary = "Cadastrar região")
    public ResponseEntity<EntityModel<RegiaoResponseDTO>> criar(
            @RequestBody @Valid RegiaoRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        adicionarLinks(
                                service.criar(dto)
                        )
                );

    }

    @GetMapping
    @Operation(summary = "Listar regiões")
    public ResponseEntity<CollectionModel<EntityModel<RegiaoResponseDTO>>> listar() {

        List<EntityModel<RegiaoResponseDTO>> regioes =
                service.listar()
                        .stream()
                        .map(this::adicionarLinks)
                        .toList();

        return ResponseEntity.ok(
                CollectionModel.of(
                        regioes,
                        linkTo(
                                methodOn(RegiaoController.class)
                                        .listar()
                        ).withSelfRel()
                )
        );

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar região por ID")
    public ResponseEntity<EntityModel<RegiaoResponseDTO>> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                adicionarLinks(
                        service.buscarPorId(id)
                )
        );

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar região")
    public ResponseEntity<EntityModel<RegiaoResponseDTO>> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RegiaoRequestDTO dto
    ) {

        return ResponseEntity.ok(
                adicionarLinks(
                        service.atualizar(id, dto)
                )
        );

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover região")
    public ResponseEntity<Void> remover(
            @PathVariable Long id
    ) {

        service.remover(id);

        return ResponseEntity
                .noContent()
                .build();

    }

    private EntityModel<RegiaoResponseDTO> adicionarLinks(
            RegiaoResponseDTO regiao
    ) {

        return EntityModel.of(
                regiao,

                linkTo(
                        methodOn(RegiaoController.class)
                                .buscarPorId(regiao.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(RegiaoController.class)
                                .listar()
                ).withRel("todas-regioes"),

                linkTo(
                        methodOn(RegiaoController.class)
                                .criar(null)
                ).withRel("criar-regiao"),

                linkTo(
                        methodOn(RegiaoController.class)
                                .atualizar(
                                        regiao.getId(),
                                        null
                                )
                ).withRel("atualizar-regiao"),

                linkTo(
                        methodOn(RegiaoController.class)
                                .remover(
                                        regiao.getId()
                                )
                ).withRel("remover-regiao")
        );

    }

}