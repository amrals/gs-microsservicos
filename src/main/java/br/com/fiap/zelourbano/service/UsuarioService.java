package br.com.fiap.zelourbano.service;

import br.com.fiap.zelourbano.domain.entity.Regiao;
import br.com.fiap.zelourbano.domain.entity.Usuario;
import br.com.fiap.zelourbano.dto.request.UsuarioRequestDTO;
import br.com.fiap.zelourbano.dto.response.UsuarioResponseDTO;
import br.com.fiap.zelourbano.exception.ResourceNotFoundException;
import br.com.fiap.zelourbano.repository.RegiaoRepository;
import br.com.fiap.zelourbano.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RegiaoRepository regiaoRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, RegiaoRepository regiaoRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.regiaoRepository = regiaoRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com ID: " + dto.getRegiaoId()));
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setRegiao(regiao);
        return toResponseDTO(usuarioRepository.save(usuario));
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        return toResponseDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com ID: " + dto.getRegiaoId()));
        modelMapper.map(dto, usuario);
        usuario.setRegiao(regiao);
        return toResponseDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = modelMapper.map(usuario, UsuarioResponseDTO.class);
        dto.setRegiaoId(usuario.getRegiao().getId());
        dto.setNomeBairroRegiao(usuario.getRegiao().getNomeBairro());
        return dto;
    }
}