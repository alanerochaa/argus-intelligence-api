package br.com.fiap.argus.service;

import br.com.fiap.argus.client.ClienteNASAFirms;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngestaoService {

    private final ClienteNASAFirms clienteNASAFirms;

    public String sincronizar24Horas() {

        return clienteNASAFirms
                .buscarFocosCalorUltimas24Horas();

    }

    public String sincronizar5Dias() {

        return clienteNASAFirms
                .buscarFocosCalorUltimos5Dias();

    }

}