package br.com.fiap.argus.service;

import br.com.fiap.argus.domain.FocoCalor;
import br.com.fiap.argus.domain.Regiao;
import br.com.fiap.argus.dto.request.FocoCalorRequestDTO;
import br.com.fiap.argus.dto.response.FocoCalorResponseDTO;
import br.com.fiap.argus.exception.ResourceNotFoundException;
import br.com.fiap.argus.mapper.FocoCalorMapper;
import br.com.fiap.argus.repository.FocoCalorRepository;
import br.com.fiap.argus.repository.RegiaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FocoCalorService {

    private final FocoCalorRepository repository;
    private final RegiaoRepository regiaoRepository;

    public FocoCalorResponseDTO criar(FocoCalorRequestDTO dto) {
        Regiao regiao = buscarRegiao(dto.regiaoId());

        FocoCalor focoCalor = FocoCalorMapper.toEntity(dto, regiao);
        FocoCalor focoCalorSalvo = repository.save(focoCalor);

        return FocoCalorMapper.toResponse(focoCalorSalvo);
    }

    public List<FocoCalorResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(FocoCalorMapper::toResponse)
                .toList();
    }

    private Regiao buscarRegiao(Long id) {
        return regiaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com ID: " + id));
    }
}