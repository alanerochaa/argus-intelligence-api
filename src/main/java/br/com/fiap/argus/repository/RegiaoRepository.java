package br.com.fiap.argus.repository;

import br.com.fiap.argus.domain.Regiao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegiaoRepository
        extends JpaRepository<Regiao, Long> {

    List<Regiao> findByEstado(
            String estado
    );

}