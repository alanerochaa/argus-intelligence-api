package br.com.fiap.argus.service;

import br.com.fiap.argus.domain.*;
import br.com.fiap.argus.dto.request.*;
import br.com.fiap.argus.dto.response.*;
import br.com.fiap.argus.mapper.*;
import br.com.fiap.argus.repository.*;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegiaoService {

    private final RegiaoRepository repository;
    private final BiomaRepository biomaRepository;

    public RegiaoResponseDTO criar(
            RegiaoRequestDTO dto
    ) {

        Bioma bioma =
                biomaRepository.findById(
                                dto.biomaId()
                        )
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "Bioma não encontrado."
                                        )
                        );

        Regiao regiao =
                RegiaoMapper.toEntity(
                        dto,
                        bioma
                );

        return RegiaoMapper.toResponse(
                repository.save(
                        regiao
                )
        );
    }

    public List<RegiaoResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(
                        RegiaoMapper::toResponse
                )
                .toList();
    }

}