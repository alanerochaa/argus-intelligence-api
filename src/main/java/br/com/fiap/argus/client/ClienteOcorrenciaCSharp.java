package br.com.fiap.argus.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ocorrencia-client",
        url = "${csharp.api.url}"
)
public interface ClienteOcorrenciaCSharp {

    @GetMapping("/api/v1/ocorrencias")
    Object listarOcorrencias();

    @GetMapping("/api/v1/ocorrencias/{id}")
    Object buscarOcorrenciaPorId(
            @PathVariable Long id
    );

    @GetMapping("/api/v1/registros-campo")
    Object listarRegistrosCampo();

    @GetMapping("/api/v1/brigadistas/{id}")
    Object buscarBrigadista(
            @PathVariable Long id
    );
}