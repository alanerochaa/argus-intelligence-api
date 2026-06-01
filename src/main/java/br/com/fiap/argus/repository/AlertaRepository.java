package br.com.fiap.argus.repository;

import br.com.fiap.argus.domain.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    List<Alerta> findByStatus(String status);

    List<Alerta> findByNivel(String nivel);
}