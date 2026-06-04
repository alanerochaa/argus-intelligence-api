package br.com.fiap.argus.service;

import br.com.fiap.argus.domain.Alerta;
import br.com.fiap.argus.domain.FocoCalor;
import br.com.fiap.argus.dto.request.AlertaRequestDTO;
import br.com.fiap.argus.dto.response.AlertaResponseDTO;
import br.com.fiap.argus.exception.ResourceNotFoundException;
import br.com.fiap.argus.mapper.AlertaMapper;
import br.com.fiap.argus.messaging.ProdutorAlerta;
import br.com.fiap.argus.repository.AlertaRepository;
import br.com.fiap.argus.repository.FocoCalorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final FocoCalorRepository focoCalorRepository;
    private final ProdutorAlerta produtorAlerta;

    public AlertaResponseDTO criar(AlertaRequestDTO dto) {
        FocoCalor focoCalor = buscarFocoCalor(dto.focoCalorId());

        Alerta alerta = AlertaMapper.toEntity(dto, focoCalor);
        Alerta alertaSalvo = alertaRepository.save(alerta);

        AlertaResponseDTO response = AlertaMapper.toResponse(alertaSalvo);

        produtorAlerta.enviarAlerta(response);

        return response;
    }

    public List<AlertaResponseDTO> listar() {
        return alertaRepository.findAll()
                .stream()
                .map(AlertaMapper::toResponse)
                .toList();
    }

    public AlertaResponseDTO buscarPorId(Long id) {
        Alerta alerta = buscarAlerta(id);
        return AlertaMapper.toResponse(alerta);
    }

    public AlertaResponseDTO atualizar(Long id, AlertaRequestDTO dto) {
        Alerta alerta = buscarAlerta(id);
        FocoCalor focoCalor = buscarFocoCalor(dto.focoCalorId());

        AlertaMapper.updateEntity(alerta, dto, focoCalor);

        Alerta alertaAtualizado = alertaRepository.save(alerta);

        return AlertaMapper.toResponse(alertaAtualizado);
    }

    public void remover(Long id) {
        Alerta alerta = buscarAlerta(id);
        alertaRepository.delete(alerta);
    }

    private Alerta buscarAlerta(Long id) {
        return alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com ID: " + id));
    }

    private FocoCalor buscarFocoCalor(Long id) {
        return focoCalorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Foco de calor não encontrado com ID: " + id));
    }
}