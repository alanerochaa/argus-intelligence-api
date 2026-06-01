package br.com.fiap.argus.client;

import br.com.fiap.argus.dto.request.IARequestDTO;
import br.com.fiap.argus.dto.response.IAResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ia-client",
        url = "${ia.api.url}"
)
public interface ClienteIA {

    @PostMapping("/api/ia/gerar-relatorio")
    IAResponseDTO gerarRelatorio(
            @RequestBody IARequestDTO dto
    );

    @PostMapping("/api/ia/consultar")
    IAResponseDTO consultarProcedimento(
            @RequestBody IARequestDTO dto
    );
}