package br.com.fiap.argus.service;

import br.com.fiap.argus.client.ClienteOcorrenciaCSharp;
import br.com.fiap.argus.dto.response.*;
import br.com.fiap.argus.exception.BusinessException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationsCSharpService {

    private static final String INTEGRACAO_DESABILITADA =
            "Integração Operations (.NET) temporariamente desabilitada.";

    private static final String API_INDISPONIVEL =
            "API Operations (.NET) indisponível no momento.";

    private final ClienteOcorrenciaCSharp cliente;

    @Value("${csharp.integration.enabled:false}")
    private boolean integrationEnabled;

    private void validarIntegracao() {
        if (!integrationEnabled) {
            throw new BusinessException(INTEGRACAO_DESABILITADA);
        }
    }

    public List<OcorrenciaCSharpResponseDTO> listarOcorrencias() {
        validarIntegracao();

        try {
            return cliente.listarOcorrencias();
        } catch (FeignException ex) {
            throw new BusinessException(API_INDISPONIVEL);
        }
    }

    public OcorrenciaCSharpResponseDTO buscarOcorrenciaPorId(Long id) {
        validarIntegracao();

        try {
            return cliente.buscarOcorrenciaPorId(id);
        } catch (FeignException.NotFound ex) {
            throw new BusinessException("Ocorrência não encontrada na API Operations (.NET).");
        } catch (FeignException ex) {
            throw new BusinessException(API_INDISPONIVEL);
        }
    }

    public List<RegistroCampoCSharpResponseDTO> listarRegistrosCampo() {
        validarIntegracao();

        try {
            return cliente.listarRegistrosCampo();
        } catch (FeignException ex) {
            throw new BusinessException(API_INDISPONIVEL);
        }
    }

    public BrigadistaCSharpResponseDTO buscarBrigadistaPorId(Long id) {
        validarIntegracao();

        try {
            return cliente.buscarBrigadista(id);
        } catch (FeignException.NotFound ex) {
            throw new BusinessException("Brigadista não encontrado na API Operations (.NET).");
        } catch (FeignException ex) {
            throw new BusinessException(API_INDISPONIVEL);
        }
    }
}