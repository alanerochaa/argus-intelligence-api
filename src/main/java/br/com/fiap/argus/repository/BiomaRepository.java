package br.com.fiap.argus.repository;

import br.com.fiap.argus.domain.Bioma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BiomaRepository
        extends JpaRepository<Bioma, Long> {

    Optional<Bioma> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);
}