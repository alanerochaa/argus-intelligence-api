package br.com.fiap.argus.service;

import br.com.fiap.argus.client.ClienteOcorrenciaCSharp;
import br.com.fiap.argus.dto.response.*;
import br.com.fiap.argus.exception.BusinessException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationsCSharpService {

    private final ClienteOcorrenciaCSharp cliente;

    public List<OcorrenciaCSharpResponseDTO> listarOcorrencias() {
        try {
            return cliente.listarOcorrencias();
        } catch (FeignException ex) {
            throw new BusinessException("API Operations (.NET) indisponível no momento.");
        }
    }

    public OcorrenciaCSharpResponseDTO buscarOcorrenciaPorId(Long id) {
        try {
            return cliente.buscarOcorrenciaPorId(id);
        } catch (FeignException.NotFound ex) {
            throw new BusinessException("Ocorrência não encontrada na API Operations (.NET).");
        } catch (FeignException ex) {
            throw new BusinessException("API Operations (.NET) indisponível no momento.");
        }
    }

    public List<RegistroCampoCSharpResponseDTO> listarRegistrosCampo() {
        try {
            return cliente.listarRegistrosCampo();
        } catch (FeignException ex) {
            throw new BusinessException("API Operations (.NET) indisponível no momento.");
        }
    }

    public BrigadistaCSharpResponseDTO buscarBrigadistaPorId(Long id) {
        try {
            return cliente.buscarBrigadista(id);
        } catch (FeignException.NotFound ex) {
            throw new BusinessException("Brigadista não encontrado na API Operations (.NET).");
        } catch (FeignException ex) {
            throw new BusinessException("API Operations (.NET) indisponível no momento.");
        }
    }
}