package br.com.fiap.zelourbano.repository;
import br.com.fiap.zelourbano.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}