package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.AlertaRequestDTO;
import br.com.fiap.argus.dto.response.AlertaResponseDTO;
import br.com.fiap.argus.service.AlertaService;
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
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
@Tag(
        name = "Domínio ARGUS - Alertas",
        description = """
        Gerencia os alertas ambientais do ARGUS.

        Os alertas representam eventos de risco
        gerados a partir dos focos de calor monitorados.

        Observação:
        alertas com nível ALTO ou CRITICO são publicados
        automaticamente na fila RabbitMQ argus.alertas.
        """
)
public class AlertaController {

    private final AlertaService service;

    @GetMapping
    @Operation(
            summary = "Listar alertas",
            description = "Retorna todos os alertas cadastrados no sistema com links HATEOAS."
    )
    public ResponseEntity<CollectionModel<EntityModel<AlertaResponseDTO>>> listar() {

        List<EntityModel<AlertaResponseDTO>> alertas =
                service.listar()
                        .stream()
                        .map(this::adicionarLinks)
                        .toList();

        CollectionModel<EntityModel<AlertaResponseDTO>> collection =
                CollectionModel.of(
                        alertas,
                        linkTo(
                                methodOn(AlertaController.class)
                                        .listar()
                        ).withSelfRel()
                );

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar alerta por ID",
            description = "Consulta um alerta específico utilizando seu identificador e retorna links HATEOAS."
    )
    public ResponseEntity<EntityModel<AlertaResponseDTO>> buscarPorId(
            @PathVariable Long id
    ) {

        AlertaResponseDTO alerta =
                service.buscarPorId(id);

        return ResponseEntity.ok(
                adicionarLinks(alerta)
        );
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar alerta",
            description = """
            Cria um novo alerta ambiental no ARGUS.

            Para validar a mensageria com RabbitMQ, use nível ALTO ou CRITICO.
            Exemplo de payload:

            {
              "titulo": "Teste RabbitMQ",
              "descricao": "Teste integração C#",
              "nivel": "CRITICO",
              "status": "ABERTO",
              "scoreRisco": 95.0,
              "recomendacaoOperacional": "Acionar brigada",
              "focoCalorId": 1
            }
            """
    )
    public ResponseEntity<EntityModel<AlertaResponseDTO>> criar(
            @RequestBody
            @Valid
            AlertaRequestDTO dto
    ) {

        AlertaResponseDTO alerta =
                service.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        adicionarLinks(alerta)
                );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar alerta",
            description = "Atualiza os dados de um alerta já existente."
    )
    public ResponseEntity<EntityModel<AlertaResponseDTO>> atualizar(
            @PathVariable Long id,
            @RequestBody
            @Valid
            AlertaRequestDTO dto
    ) {

        AlertaResponseDTO alerta =
                service.atualizar(
                        id,
                        dto
                );

        return ResponseEntity.ok(
                adicionarLinks(alerta)
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

    private EntityModel<AlertaResponseDTO> adicionarLinks(
            AlertaResponseDTO alerta
    ) {

        EntityModel<AlertaResponseDTO> model =
                EntityModel.of(alerta);

        model.add(
                linkTo(
                        methodOn(AlertaController.class)
                                .buscarPorId(alerta.id())
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(AlertaController.class)
                                .listar()
                ).withRel("todos-alertas")
        );

        model.add(
                linkTo(
                        methodOn(AlertaController.class)
                                .criar(null)
                ).withRel("criar-alerta")
        );

        model.add(
                linkTo(
                        methodOn(AlertaController.class)
                                .atualizar(alerta.id(), null)
                ).withRel("atualizar-alerta")
        );

        model.add(
                linkTo(
                        methodOn(AlertaController.class)
                                .remover(alerta.id())
                ).withRel("remover-alerta")
        );

        return model;
    }
}