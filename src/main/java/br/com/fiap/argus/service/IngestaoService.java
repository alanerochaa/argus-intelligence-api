package br.com.fiap.argus.service;

import br.com.fiap.argus.client.ClienteNASAFirms;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngestaoService {

    private final ClienteNASAFirms clienteNASAFirms;

    public String consumirFocosCalorUltimas24Horas() {

        String response =
                clienteNASAFirms
                        .buscarFocosCalorUltimas24Horas();

        return response;

    }

    public String consumirFocosCalorUltimos5Dias() {

        String response =
                clienteNASAFirms
                        .buscarFocosCalorUltimos5Dias();

        return response;

    }

}