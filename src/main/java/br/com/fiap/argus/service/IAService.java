package br.com.fiap.argus.service;

import br.com.fiap.argus.client.ClienteIA;
import br.com.fiap.argus.dto.request.IARequestDTO;
import br.com.fiap.argus.dto.response.IAResponseDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IAService {

    private final ClienteIA clienteIA;

    public IAResponseDTO gerarRelatorio(
            IARequestDTO dto
    ) {

        return clienteIA
                .gerarRelatorio(dto);

    }

    public IAResponseDTO consultar(
            IARequestDTO dto
    ) {

        return clienteIA
                .consultarProcedimento(dto);

    }

}