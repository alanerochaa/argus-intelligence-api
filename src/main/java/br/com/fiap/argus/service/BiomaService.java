package br.com.fiap.argus.service;

import br.com.fiap.argus.domain.Bioma;
import br.com.fiap.argus.dto.request.BiomaRequestDTO;
import br.com.fiap.argus.dto.response.BiomaResponseDTO;
import br.com.fiap.argus.exception.BusinessException;
import br.com.fiap.argus.exception.ResourceNotFoundException;
import br.com.fiap.argus.mapper.BiomaMapper;
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
        Bioma bioma = buscarBioma(id);
        return mapper.toResponse(bioma);
    }

    public BiomaResponseDTO cadastrar(BiomaRequestDTO dto) {
        validarNomeDuplicado(dto.getNome());

        Bioma bioma = mapper.toEntity(dto);
        Bioma biomaSalvo = repository.save(bioma);

        return mapper.toResponse(biomaSalvo);
    }

    public BiomaResponseDTO atualizar(Long id, BiomaRequestDTO dto) {
        Bioma bioma = buscarBioma(id);

        mapper.updateEntity(bioma, dto);

        Bioma biomaAtualizado = repository.save(bioma);

        return mapper.toResponse(biomaAtualizado);
    }

    public void deletar(Long id) {
        Bioma bioma = buscarBioma(id);
        repository.delete(bioma);
    }

    private Bioma buscarBioma(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bioma não encontrado com ID: " + id));
    }

    private void validarNomeDuplicado(String nome) {
        if (repository.existsByNomeIgnoreCase(nome)) {
            throw new BusinessException("Já existe um bioma com esse nome.");
        }
    }
}