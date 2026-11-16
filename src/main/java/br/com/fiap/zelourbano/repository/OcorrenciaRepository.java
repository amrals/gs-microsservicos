package br.com.fiap.zelourbano.repository;
import br.com.fiap.zelourbano.domain.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {}