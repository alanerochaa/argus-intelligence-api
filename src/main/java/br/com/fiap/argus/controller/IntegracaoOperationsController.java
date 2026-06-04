package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.response.BrigadistaCSharpResponseDTO;
import br.com.fiap.argus.dto.response.OcorrenciaCSharpResponseDTO;
import br.com.fiap.argus.dto.response.RegistroCampoCSharpResponseDTO;
import br.com.fiap.argus.service.OperationsCSharpService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/integracao")
@RequiredArgsConstructor
@Tag(
        name = "Integrações Externas - API Operations (.NET)",
        description = "Consome dados operacionais da API C#/.NET."
)
public class IntegracaoOperationsController {

    private final OperationsCSharpService service;

    @Operation(
            summary = "Consumir ocorrências da API C#",
            description = "Busca todas as ocorrências registradas na API ARGUS Operations (.NET)."
    )
    @GetMapping("/ocorrencias")
    public List<OcorrenciaCSharpResponseDTO> listarOcorrencias() {
        return service.listarOcorrencias();
    }

    @Operation(
            summary = "Consumir ocorrência por ID da API C#",
            description = "Consulta uma ocorrência específica na API ARGUS Operations (.NET)."
    )
    @GetMapping("/ocorrencias/{id}")
    public OcorrenciaCSharpResponseDTO buscarOcorrenciaPorId(
            @PathVariable Long id
    ) {
        return service.buscarOcorrenciaPorId(id);
    }

    @Operation(
            summary = "Consumir registros de campo da API C#",
            description = "Busca evidências operacionais, como foto, GPS e observações, na API ARGUS Operations (.NET)."
    )
    @GetMapping("/registros")
    public List<RegistroCampoCSharpResponseDTO> listarRegistrosCampo() {
        return service.listarRegistrosCampo();
    }

    @Operation(
            summary = "Consumir brigadista por ID da API C#",
            description = "Consulta os dados do brigadista responsável na API ARGUS Operations (.NET)."
    )
    @GetMapping("/brigadistas/{id}")
    public BrigadistaCSharpResponseDTO buscarBrigadistaPorId(
            @PathVariable Long id
    ) {
        return service.buscarBrigadistaPorId(id);
    }
}