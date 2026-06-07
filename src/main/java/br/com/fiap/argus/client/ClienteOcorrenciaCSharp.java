package br.com.fiap.argus.client;

import br.com.fiap.argus.config.CSharpFeignConfig;
import br.com.fiap.argus.dto.response.BrigadistaCSharpResponseDTO;
import br.com.fiap.argus.dto.response.OcorrenciaCSharpResponseDTO;
import br.com.fiap.argus.dto.response.RegistroCampoCSharpResponseDTO;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "ocorrencia-client",
        url = "${csharp.api.url}",
        configuration = CSharpFeignConfig.class
)
public interface ClienteOcorrenciaCSharp {

    @GetMapping("/api/ocorrencias")
    List<OcorrenciaCSharpResponseDTO> listarOcorrencias();

    @GetMapping("/api/ocorrencias/{id}")
    OcorrenciaCSharpResponseDTO buscarOcorrenciaPorId(
            @PathVariable Long id
    );

    @GetMapping("/api/registroscampo")
    List<RegistroCampoCSharpResponseDTO> listarRegistrosCampo();

    @GetMapping("/api/brigadistas/{id}")
    BrigadistaCSharpResponseDTO buscarBrigadista(
            @PathVariable Long id
    );

}