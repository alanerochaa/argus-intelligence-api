package br.com.fiap.argus.service;

import br.com.fiap.argus.client.ClienteOcorrenciaCSharp;
import br.com.fiap.argus.dto.response.*;
import br.com.fiap.argus.exception.BusinessException;
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

        } catch (Exception ex) {

            throw new BusinessException(
                    "Erro ao consumir ocorrências da API Operations (.NET)."
            );

        }

    }

    public OcorrenciaCSharpResponseDTO buscarOcorrenciaPorId(
            Long id
    ) {

        try {

            return cliente.buscarOcorrenciaPorId(
                    id
            );

        } catch (Exception ex) {

            throw new BusinessException(
                    "Erro ao buscar ocorrência na API Operations (.NET)."
            );

        }

    }

    public List<RegistroCampoCSharpResponseDTO> listarRegistrosCampo() {

        try {

            return cliente.listarRegistrosCampo();

        } catch (Exception ex) {

            throw new BusinessException(
                    "Erro ao consumir registros de campo da API Operations (.NET)."
            );

        }

    }

    public BrigadistaCSharpResponseDTO buscarBrigadistaPorId(
            Long id
    ) {

        try {

            return cliente.buscarBrigadista(
                    id
            );

        } catch (Exception ex) {

            throw new BusinessException(
                    "Erro ao buscar brigadista na API Operations (.NET)."
            );

        }

    }

}