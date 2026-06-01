package br.com.fiap.argus.service;

import br.com.fiap.argus.domain.*;
import br.com.fiap.argus.dto.request.FocoCalorRequestDTO;
import br.com.fiap.argus.dto.response.FocoCalorResponseDTO;
import br.com.fiap.argus.mapper.FocoCalorMapper;
import br.com.fiap.argus.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FocoCalorService {

    private final FocoCalorRepository repository;
    private final RegiaoRepository regiaoRepository;

    public FocoCalorResponseDTO criar(
            FocoCalorRequestDTO dto
    ) {

        Regiao regiao =
                regiaoRepository.findById(
                                dto.regiaoId()
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Região não encontrada."
                                )
                        );

        return FocoCalorMapper.toResponse(
                repository.save(
                        FocoCalorMapper.toEntity(
                                dto,
                                regiao
                        )
                )
        );
    }

    public List<FocoCalorResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(FocoCalorMapper::toResponse)
                .toList();
    }

}