package br.com.fiap.argus.service;

import br.com.fiap.argus.client.ClienteIA;

import br.com.fiap.argus.dto.request.IARequestConsultaDTO;
import br.com.fiap.argus.dto.request.IARequestRelatorioDTO;

import br.com.fiap.argus.dto.response.IAResponseConsultaDTO;
import br.com.fiap.argus.dto.response.IAResponseRelatorioDTO;

import br.com.fiap.argus.exception.BusinessException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IAService {

    private final ClienteIA clienteIA;

    public IAResponseRelatorioDTO gerarRelatorio(
            IARequestRelatorioDTO dto
    ) {

        try {

            return clienteIA.gerarRelatorio(
                    dto
            );

        } catch (Exception ex) {

            throw new BusinessException(
                    "Erro ao gerar relatório na API de IA."
            );

        }

    }

    public IAResponseConsultaDTO consultar(
            IARequestConsultaDTO dto
    ) {

        try {

            return clienteIA.consultar(
                    dto
            );

        } catch (Exception ex) {

            throw new BusinessException(
                    "Erro ao consultar a API de IA."
            );

        }

    }

    public String health() {

        try {

            return clienteIA.health();

        } catch (Exception ex) {

            throw new BusinessException(
                    "API de IA indisponível."
            );

        }

    }

}