package br.com.fiap.argus.service;

import br.com.fiap.argus.domain.Bioma;
import br.com.fiap.argus.exception.ResourceNotFoundException;
import br.com.fiap.argus.mapper.BiomaMapper;
import br.com.fiap.argus.dto.request.BiomaRequestDTO;
import br.com.fiap.argus.dto.response.BiomaResponseDTO;
import br.com.fiap.argus.repository.BiomaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BiomaService {

    private final BiomaRepository repository;

    private final BiomaMapper mapper;

    public List<BiomaResponseDTO> listarTodos() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public BiomaResponseDTO buscarPorId(Long id) {

        Bioma bioma = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bioma não encontrado"
                        )
                );

        return mapper.toResponse(bioma);
    }

    public BiomaResponseDTO cadastrar(
            BiomaRequestDTO dto
    ) {

        if (repository.existsByNomeIgnoreCase(dto.getNome())) {

            throw new RuntimeException(
                    "Já existe um bioma com esse nome"
            );
        }

        Bioma entity = mapper.toEntity(dto);

        return mapper.toResponse(
                repository.save(entity)
        );
    }

    public BiomaResponseDTO atualizar(
            Long id,
            BiomaRequestDTO dto
    ) {

        Bioma entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bioma não encontrado"
                        )
                );

        mapper.updateEntity(entity, dto);

        return mapper.toResponse(
                repository.save(entity)
        );
    }

    public void deletar(Long id) {

        Bioma entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bioma não encontrado"
                        )
                );

        repository.delete(entity);
    }
}