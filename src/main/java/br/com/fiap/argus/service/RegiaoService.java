package br.com.fiap.argus.service;

import br.com.fiap.argus.domain.Bioma;
import br.com.fiap.argus.domain.Regiao;
import br.com.fiap.argus.dto.request.RegiaoRequestDTO;
import br.com.fiap.argus.dto.response.RegiaoResponseDTO;
import br.com.fiap.argus.exception.ResourceNotFoundException;
import br.com.fiap.argus.mapper.RegiaoMapper;
import br.com.fiap.argus.repository.BiomaRepository;
import br.com.fiap.argus.repository.RegiaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegiaoService {

    private final RegiaoRepository repository;
    private final BiomaRepository biomaRepository;

    public RegiaoResponseDTO criar(RegiaoRequestDTO dto) {
        Bioma bioma = buscarBioma(dto.biomaId());

        Regiao regiao = RegiaoMapper.toEntity(dto, bioma);
        Regiao regiaoSalva = repository.save(regiao);

        return RegiaoMapper.toResponse(regiaoSalva);
    }

    public List<RegiaoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(RegiaoMapper::toResponse)
                .toList();
    }

    private Bioma buscarBioma(Long id) {
        return biomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bioma não encontrado com ID: " + id));
    }
}