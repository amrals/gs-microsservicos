package br.com.fiap.zelourbano.repository;
import br.com.fiap.zelourbano.domain.entity.SolicitacaoPrefeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SolicitacaoPrefeituraRepository extends JpaRepository<SolicitacaoPrefeitura, Long> {
    Optional<SolicitacaoPrefeitura> findByOcorrenciaId(Long ocorrenciaId);
}