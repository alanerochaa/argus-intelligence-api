package br.com.fiap.argus.service;

import br.com.fiap.argus.client.ClienteWeather;

import br.com.fiap.argus.domain.Regiao;

import br.com.fiap.argus.dto.response.RiscoResponseDTO;

import br.com.fiap.argus.repository.FocoCalorRepository;

import br.com.fiap.argus.repository.RegiaoRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiscoService {

    private final RegiaoRepository regiaoRepository;

    private final FocoCalorRepository focoCalorRepository;

    private final ClienteWeather clienteWeather;

    public RiscoResponseDTO calcularRisco(
            Long id
    ) {

        Regiao regiao =
                regiaoRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "Região não encontrada."
                                        )
                        );

        long quantidadeFocos =
                focoCalorRepository
                        .countByRegiaoId(id);

        double score =
                calcularScore(
                        regiao.getNivelRisco(),
                        quantidadeFocos
                );

        String clima =
                regiao.getLatitudeCentral() != null
                        && regiao.getLongitudeCentral() != null

                        ? clienteWeather.buscarClima(
                        regiao.getLatitudeCentral(),
                        regiao.getLongitudeCentral()
                )

                        : "Clima indisponível";

        return new RiscoResponseDTO(

                regiao.getId(),

                regiao.getNome(),

                regiao.getEstado(),

                regiao.getNivelRisco(),

                quantidadeFocos,

                score,

                clima

        );

    }

    private double calcularScore(

            String nivel,

            long focos

    ) {

        double base =
                switch (nivel) {

                    case "MEDIO" -> 35;

                    case "ALTO" -> 65;

                    case "CRITICO" -> 85;

                    default -> 15;

                };

        return Math.min(
                base + (focos * 5),
                100
        );

    }

}