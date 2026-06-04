package br.com.fiap.argus.client;

import br.com.fiap.argus.dto.request.*;
import br.com.fiap.argus.dto.response.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "clienteIA",
        url = "${ia.api.url}"
)
public interface ClienteIA {

    @PostMapping("/api/v1/ia/gerar-relatorio")
    IAResponseRelatorioDTO gerarRelatorio(
            @RequestBody IARequestRelatorioDTO dto
    );

    @PostMapping("/api/v1/ia/consultar")
    IAResponseConsultaDTO consultar(
            @RequestBody IARequestConsultaDTO dto
    );

    @GetMapping("/api/v1/ia/health")
    String health();
}