package br.com.fiap.argus.repository;

import br.com.fiap.argus.domain.FocoCalor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FocoCalorRepository
        extends JpaRepository<FocoCalor, Long> {

    long countByRegiaoId(Long regiaoId);
}